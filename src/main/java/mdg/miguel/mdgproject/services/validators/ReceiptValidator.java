package mdg.miguel.mdgproject.services.validators;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mdg.miguel.mdgproject.dtos.PaymentReceiptDTO;
import mdg.miguel.mdgproject.exceptions.ReceiptValidationException;

@Component
public class ReceiptValidator {

  public void validate(PaymentReceiptDTO dto) {
    Map<String, String> errors = new LinkedHashMap<>();

    Map<Supplier<Boolean>, String> validations = new LinkedHashMap<>();
    validations.put(() -> dto.description() == null || dto.description().trim().isEmpty(),
        "Descrição é um campo obrigatório");
    validations.put(() -> dto.paymentType() == null, "Tipo de pagamento é um campo obrigatório");
    validations.put(() -> dto.paymentMethod() == null, "Método de pagamento é um campo obrigatório");
    validations.put(() -> dto.paymentDate() == null, "Data é um campo obrigatório");
    validations.put(() -> dto.paymentDate().isAfter(LocalDate.now()), "Data inválida: não pode ser futura");
    validations.put(() -> dto.paymentAmount() == null || dto.paymentAmount() <= 0,
        "Valor do pagamento inválido, verifique o valor informado.");
    validations.put(() -> dto.receiptFile() == null || dto.receiptFile().isEmpty(),
        "Comprovante de pagamento é obrigatório");

    validations.forEach((condition, message) -> {
      if (condition.get()) {
        errors.put(message.split(" ")[0].toLowerCase(), message);
      }
    });

    if (!errors.isEmpty()) {
      throw new ReceiptValidationException(errors);
    }
  }
}