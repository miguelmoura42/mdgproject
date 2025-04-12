package mdg.miguel.mdgproject.services.validators;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import mdg.miguel.mdgproject.dtos.ResellerDTO;
import mdg.miguel.mdgproject.exceptions.ResellerValidatorException;
import mdg.miguel.mdgproject.repositories.ResellerRepository;

@Component
public class ResellerValidator {

  private ResellerRepository resellerRepository;

  public ResellerValidator(ResellerRepository resellerRepository) {
    this.resellerRepository = resellerRepository;
  }

  public void validateDto(ResellerDTO dto) {
    Map<String, String> errors = new LinkedHashMap<>();

    Map<Supplier<Boolean>, String> validations = new LinkedHashMap<>();
    validations.put(() -> dto.getName() == null || dto.getName().trim().isEmpty(), "Nome é um campo obrigatório!");
    validations.put(() -> resellerRepository.existsByName(dto.getName()), "Revendedor já existente!");
    validations.put(() -> resellerRepository.existsByUniqueKey(dto.getUniqueKey()), "Chave única já existente!");
    validations.put(() -> dto.getCity() == null, "Cidade é um campo obrigatório!");

    validations.forEach((condition, message) -> {
      if (condition.get()) {
        errors.put(message.split(" ")[0].toLowerCase(), message);
      }
    });

    if (!errors.isEmpty()) {
      throw new ResellerValidatorException(errors);
    }
  }

}
