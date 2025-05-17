package mdg.miguel.mdgproject.services.validators;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import mdg.miguel.mdgproject.dtos.ProductionDTO;
import mdg.miguel.mdgproject.exceptions.ProductionValidationException;
import mdg.miguel.mdgproject.repositories.ProductionRepository;

@Component
public class ProductionValidator {

  private final ProductionRepository repository;

  public ProductionValidator(ProductionRepository repository) {
    this.repository = repository;
  }

  public void validate(ProductionDTO dto) {
    Map<String, String> errors = new LinkedHashMap<>();

    Map<String, Supplier<Boolean>> validations = new LinkedHashMap<>();
    validations.put("data.nula", () -> dto.getDate() == null);
    validations.put("data.futura", () -> dto.getDate() != null && dto.getDate().isAfter(LocalDate.now()));
    validations.put("manha.invalida", () -> dto.getMorningProduction() == null || dto.getMorningProduction() < 0);
    validations.put("tarde.invalida", () -> dto.getAfternoonProduction() == null || dto.getAfternoonProduction() < 0);
    validations.put("data.duplicada", () -> dto.getDate() != null && repository.existsByDate(dto.getDate()));

    Map<String, String> messages = Map.of(
        "data.nula", "A data não pode ser nula.",
        "data.futura", "A data não pode estar no futuro.",
        "manha.invalida", "Produção da manhã inválida (nula ou negativa).",
        "tarde.invalida", "Produção da tarde inválida (nula ou negativa).",
        "data.duplicada", "Já existe produção cadastrada para essa data.");

    validations.forEach((key, condition) -> {
      if (condition.get()) {
        errors.put(key, messages.get(key));
      }
    });

    if (!errors.isEmpty()) {
      throw new ProductionValidationException(errors);
    }
  }
}
