package mdg.miguel.mdgproject.exceptions;

public class ReceiptFileStorageException extends RuntimeException {

  public ReceiptFileStorageException(String message) {
    super(message);
  }

  public ReceiptFileStorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
