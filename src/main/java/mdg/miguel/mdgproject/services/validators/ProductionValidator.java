package mdg.miguel.mdgproject.services.validators;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import mdg.miguel.mdgproject.dtos.ProductionDTO;
import mdg.miguel.mdgproject.exceptions.ProductionValidationExption;
import mdg.miguel.mdgproject.repositories.ProductionRepository;

@Component
public class ProductionValidator {

  private ProductionRepository repository;

  public ProductionValidator(ProductionRepository repository) {
    this.repository = repository;
  }

  public void validate(ProductionDTO dto) {

    Map<String, String> errors = new LinkedHashMap<>();

    Map<Supplier<Boolean>, String> validations = new LinkedHashMap<>();
    validations.put(() -> dto.getDate() == null || dto.getDate().isAfter(LocalDate.now()), "Data inválida!");
    validations.put(() -> dto.getMorningProduction() == null || dto.getMorningProduction() < 0, "Produção inválida!");
    validations.put(() -> dto.getAfternoonProduction() == null || dto.getAfternoonProduction() < 0,
        "Produção inválida!");
    validations.put(() -> repository.existsByDate(dto.getDate()), "Data já tem produção cadastrada!");

    validations.forEach((condition, message) -> {
      if (condition.get()) {
        errors.put(message.split(" ")[0].toLowerCase(), message);
      }
    });

    if (!errors.isEmpty()) {
      throw new ProductionValidationExption(errors);
    }
  }

}
