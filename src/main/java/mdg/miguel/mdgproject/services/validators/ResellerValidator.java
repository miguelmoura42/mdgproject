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

  private final ResellerRepository resellerRepository;

  public ResellerValidator(ResellerRepository resellerRepository) {
    this.resellerRepository = resellerRepository;
  }

  public void validateDto(ResellerDTO dto) {
    Map<String, String> errors = new LinkedHashMap<>();

    String name = dto.getName() != null ? dto.getName().trim().toUpperCase() : null;

    Map<String, Supplier<Boolean>> validations = new LinkedHashMap<>();
    validations.put("nome.nulo", () -> name == null || name.isEmpty());
    validations.put("nome.duplicado", () -> name != null && resellerRepository.existsByName(name));
    validations.put("chave.duplicada", () -> resellerRepository.existsByUniqueKey(dto.getUniqueKey()));
    validations.put("cidade.nula", () -> dto.getCity() == null);

    Map<String, String> messages = Map.of(
        "nome.nulo", "Nome é um campo obrigatório.",
        "nome.duplicado", "Já existe um revendedor com esse nome.",
        "chave.duplicada", "Chave única já existente.",
        "cidade.nula", "Cidade é um campo obrigatório.");

    validations.forEach((key, condition) -> {
      if (condition.get()) {
        errors.put(key, messages.get(key));
      }
    });

    if (!errors.isEmpty()) {
      throw new ResellerValidatorException(errors);
    }
  }
}
