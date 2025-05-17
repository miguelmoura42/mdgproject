package mdg.miguel.mdgproject.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mdg.miguel.mdgproject.dtos.PaymentReceiptDTO;
import mdg.miguel.mdgproject.dtos.ReceiptResponseDTO;
import mdg.miguel.mdgproject.entities.PaymentReceipt;
import mdg.miguel.mdgproject.enums.PaymentMethod;
import mdg.miguel.mdgproject.enums.PaymentType;
import mdg.miguel.mdgproject.exceptions.ReceiptFileStorageException;
import mdg.miguel.mdgproject.exceptions.ReceiptNotFoundException;
import mdg.miguel.mdgproject.repositories.ReceiptRepository;
import mdg.miguel.mdgproject.services.validators.ReceiptValidator;

@Service
public class ReceiptService {

  private static final String RECEIPT_DIRECTORY = "C:/recibos/";
  private ReceiptRepository receiptRepository;
  private ReceiptValidator receiptValidator;

  public ReceiptService(ReceiptRepository receiptRepository, ReceiptValidator receiptValidator) {
    this.receiptRepository = receiptRepository;
    this.receiptValidator = receiptValidator;
  }

  public void processAndSave(PaymentReceiptDTO receiptDTO) {
    receiptValidator.validate(receiptDTO);
    String filePath = saveReceiptFile(receiptDTO.receiptFile());

    var receipt = new PaymentReceipt();
    BeanUtils.copyProperties(receiptDTO, receipt);
    receipt.setReceiptFilePath(filePath);
    receiptRepository.save(receipt);
  }

  public List<ReceiptResponseDTO> getReceiptsByPaymentType(PaymentType paymentType, LocalDate startDate,
      LocalDate endDate) {
    validateDateRange(startDate, endDate);
    List<PaymentReceipt> receipts = receiptRepository.findByPaymentTypeAndPaymentDateBetween(paymentType, startDate,
        endDate);
    return receipts.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public List<ReceiptResponseDTO> getReceiptsByPaymentMethod(PaymentMethod paymentMethod, LocalDate startDate,
      LocalDate endDate) {
    validateDateRange(startDate, endDate);
    List<PaymentReceipt> receipts = receiptRepository.findByPaymentMethodAndPaymentDateBetween(paymentMethod, startDate,
        endDate);
    return receipts.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public Resource getReceiptFile(UUID uuid) {
    PaymentReceipt receipt = receiptRepository.findByUuid(uuid)
        .orElseThrow(() -> new ReceiptNotFoundException("Recibo não encontrado para o UUID: " + uuid));

    Path filePath = Paths.get(receipt.getReceiptFilePath());
    if (!Files.exists(filePath)) {
      throw new ReceiptFileStorageException("Arquivo não encontrado no servidor: " + filePath);
    }

    try {
      return new UrlResource(filePath.toUri());
    } catch (MalformedURLException e) {
      throw new ReceiptFileStorageException("Erro ao carregar o arquivo: " + filePath, e);
    }
  }

  private String saveReceiptFile(MultipartFile file) {
    try {
      String originalFileName = Optional.ofNullable(file.getOriginalFilename()).orElse("recibo.pdf");
      String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");

      Path receiptDir = Paths.get(RECEIPT_DIRECTORY);
      if (!Files.exists(receiptDir)) {
        Files.createDirectories(receiptDir);
      }

      String fileName = UUID.randomUUID() + "_" + sanitizedFileName;
      Path filePath = receiptDir.resolve(fileName).normalize();

      Files.write(filePath, file.getBytes());
      return filePath.toString();

    } catch (IOException e) {
      throw new ReceiptFileStorageException("Erro ao salvar o arquivo: " + file.getOriginalFilename(), e);
    }
  }

  private ReceiptResponseDTO convertToDto(PaymentReceipt receipts) {
    var response = new ReceiptResponseDTO();
    BeanUtils.copyProperties(receipts, response);
    return response;
  }

  private void validateDateRange(LocalDate startDate, LocalDate endDate) {
    if (startDate == null || endDate == null) {
      throw new IllegalArgumentException("Datas de início e fim não podem ser nulas.");
    }
    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("Data inicial não pode ser após a data final.");
    }
  }

}