package mdg.miguel.mdgproject.services.validators;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import mdg.miguel.mdgproject.dtos.OrderDTO;
import mdg.miguel.mdgproject.exceptions.OrderValidationException;

@Component
public class OrderValidator {

  public void validate(OrderDTO dto) {
    Map<String, String> errors = new LinkedHashMap<>();

    Map<String, Supplier<Boolean>> validations = new LinkedHashMap<>();
    validations.put("quantidade.invalida", () -> dto.getQuantity() == null || dto.getQuantity() <= 0);
    validations.put("valor.invalido", () -> dto.getAmount() == null || dto.getAmount() <= 0);
    validations.put("data.invalida", () -> dto.getDate() == null || dto.getDate().isAfter(LocalDate.now()));
    validations.put("status.nulo", () -> dto.getStatus() == null);
    validations.put("chave.nula", () -> dto.getUniqueKey() == null || dto.getUniqueKey().trim().isEmpty());

    Map<String, String> messages = Map.of(
        "quantidade.invalida", "Quantidade inválida (nula ou menor que 1).",
        "valor.invalido", "Valor inválido (nulo ou menor que 1).",
        "data.invalida", "Data inválida (nula ou no futuro).",
        "status.nulo", "Status do pedido não pode ser nulo.",
        "chave.nula", "Chave única do revendedor é obrigatória.");

    validations.forEach((key, condition) -> {
      if (condition.get()) {
        errors.put(key, messages.get(key));
      }
    });

    if (!errors.isEmpty()) {
      throw new OrderValidationException(errors);
    }
  }
}
