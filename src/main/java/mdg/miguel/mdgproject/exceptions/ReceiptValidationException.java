package mdg.miguel.mdgproject.exceptions;

import java.util.Map;

public class ReceiptValidationException extends RuntimeException {

  private final Map<String, String> errors;

  public ReceiptValidationException(Map<String, String> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public Map<String, String> getErrors() {
    return errors;
  }
}
