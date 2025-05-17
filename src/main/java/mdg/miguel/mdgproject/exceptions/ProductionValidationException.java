package mdg.miguel.mdgproject.exceptions;

import java.util.Map;

public class ProductionValidationException extends RuntimeException {

  private final Map<String, String> errors;

  public ProductionValidationException(Map<String, String> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

}
