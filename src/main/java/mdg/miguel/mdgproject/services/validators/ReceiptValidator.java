package mdg.miguel.mdgproject.services.validators;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import mdg.miguel.mdgproject.dtos.PaymentReceiptDTO;
import mdg.miguel.mdgproject.exceptions.ReceiptValidationException;

@Component
public class ReceiptValidator {

  public void validate(PaymentReceiptDTO dto) {
    Map<String, String> errors = new LinkedHashMap<>();

    Map<String, Supplier<Boolean>> validations = new LinkedHashMap<>();
    validations.put("descricao.vazia", () -> dto.description() == null || dto.description().trim().isEmpty());
    validations.put("tipo.nulo", () -> dto.paymentType() == null);
    validations.put("metodo.nulo", () -> dto.paymentMethod() == null);
    validations.put("data.nula", () -> dto.paymentDate() == null);
    validations.put("data.futura", () -> dto.paymentDate() != null && dto.paymentDate().isAfter(LocalDate.now()));
    validations.put("valor.invalido", () -> dto.paymentAmount() == null || dto.paymentAmount() <= 0);
    validations.put("arquivo.vazio", () -> dto.receiptFile() == null || dto.receiptFile().isEmpty());

    Map<String, String> messages = Map.of(
        "descricao.vazia", "Descrição é um campo obrigatório.",
        "tipo.nulo", "Tipo de pagamento é um campo obrigatório.",
        "metodo.nulo", "Método de pagamento é um campo obrigatório.",
        "data.nula", "A data do pagamento não pode ser nula.",
        "data.futura", "A data do pagamento não pode estar no futuro.",
        "valor.invalido", "Valor do pagamento inválido (nulo ou menor/igual a zero).",
        "arquivo.vazio", "Comprovante de pagamento é obrigatório.");

    validations.forEach((key, condition) -> {
      if (condition.get()) {
        errors.put(key, messages.get(key));
      }
    });

    if (!errors.isEmpty()) {
      throw new ReceiptValidationException(errors);
    }
  }
}
