package mdg.miguel.mdgproject.exceptions;

import java.util.Map;

public class ProductionValidationExption extends RuntimeException {

  private final Map<String, String> errors;

  public ProductionValidationExption(Map<String, String> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

}
