package mdg.miguel.mdgproject.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mdg.miguel.mdgproject.dtos.PaymentReceiptDTO;
import mdg.miguel.mdgproject.dtos.ReceiptResponseDTO;
import mdg.miguel.mdgproject.enums.PaymentMethod;
import mdg.miguel.mdgproject.enums.PaymentType;
import mdg.miguel.mdgproject.services.ReceiptService;

@RestController
@RequestMapping("/recibos")
public class ReceiptController {

  private ReceiptService receiptService;

  public ReceiptController(ReceiptService receiptService) {
    this.receiptService = receiptService;
  }

  @PostMapping("/novo-recibo")
  public ResponseEntity<String> newReceipt(@ModelAttribute PaymentReceiptDTO receiptDTO) {
    receiptService.processAndSave(receiptDTO);
    return ResponseEntity.ok("Comprovante cadastrado com sucesso!");
  }

  @GetMapping("/buscar-por-tipo")
  public ResponseEntity<List<ReceiptResponseDTO>> getByPaymentType(
      @RequestParam PaymentType paymentType,
      @RequestParam LocalDate startDate,
      @RequestParam LocalDate endDate) {
    return ResponseEntity.ok(receiptService.getReceiptsByPaymentType(paymentType, startDate, endDate));
  }

  @GetMapping("/buscar-por-metodo")
  public ResponseEntity<List<ReceiptResponseDTO>> getByPaymentMethod(
      @RequestParam PaymentMethod paymentMethod,
      @RequestParam LocalDate startDate,
      @RequestParam LocalDate endDate) {
    return ResponseEntity.ok(receiptService.getReceiptsByPaymentMethod(paymentMethod, startDate, endDate));
  }

  @GetMapping("/download/{uuid}")
  public ResponseEntity<Resource> downloadReceipt(@PathVariable UUID uuid) {
    Resource file = receiptService.getReceiptFile(uuid);
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        .body(file);
  }

}