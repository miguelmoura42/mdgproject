package mdg.miguel.mdgproject.exceptions;

import java.util.Map;

public class ResellerValidatorException extends RuntimeException {

  private final Map<String, String> errors;

  public ResellerValidatorException(Map<String, String> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

}