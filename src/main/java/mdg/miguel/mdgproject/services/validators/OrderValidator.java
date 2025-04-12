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

    Map<Supplier<Boolean>, String> validations = new LinkedHashMap<>();
    validations.put(() -> dto.getQuantity() == null || dto.getQuantity() <= 0, "Quantidade inválida!");
    validations.put(() -> dto.getAmount() == null || dto.getAmount() <= 0, "Valor inválido!");
    validations.put(() -> dto.getDate() == null || dto.getDate().isAfter(LocalDate.now()), "Data inválida!");
    validations.put(() -> dto.getStatus() == null, "Status de pedido inválido!");
    validations.put(() -> dto.getUniqueKey() == null || dto.getUniqueKey().trim().isEmpty(), "Chave única inválida!");

    validations.forEach((condition, message) -> {
      if (condition.get()) {
        errors.put(message.split(" ")[0].toLowerCase(), message);
      }
    });

    if (!errors.isEmpty()) {
      throw new OrderValidationException(errors);
    }
  }

}
