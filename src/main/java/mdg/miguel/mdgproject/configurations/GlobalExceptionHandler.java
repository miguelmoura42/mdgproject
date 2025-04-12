package mdg.miguel.mdgproject.configurations;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mdg.miguel.mdgproject.exceptions.OrderNotFoundException;
import mdg.miguel.mdgproject.exceptions.OrderValidationException;
import mdg.miguel.mdgproject.exceptions.ProductionNotFoundException;
import mdg.miguel.mdgproject.exceptions.ProductionValidationExption;
import mdg.miguel.mdgproject.exceptions.ReceiptFileStorageException;
import mdg.miguel.mdgproject.exceptions.ReceiptNotFoundException;
import mdg.miguel.mdgproject.exceptions.ReceiptValidationException;
import mdg.miguel.mdgproject.exceptions.ResellerNotFoundException;
import mdg.miguel.mdgproject.exceptions.ResellerValidatorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ReceiptValidationException.class)
  public ResponseEntity<Map<String, String>> handleReceiptValidationException(ReceiptValidationException ex) {
    return ResponseEntity.badRequest().body(ex.getErrors());
  }

  @ExceptionHandler(ResellerValidatorException.class)
  public ResponseEntity<Map<String, String>> handleResellerValidatorException(ResellerValidatorException ex) {
    return ResponseEntity.badRequest().body(ex.getErrors());
  }

  @ExceptionHandler(OrderValidationException.class)
  public ResponseEntity<Map<String, String>> handleOrderValidationException(OrderValidationException ex) {
    return ResponseEntity.badRequest().body(ex.getErrors());
  }

  @ExceptionHandler(ReceiptFileStorageException.class)
  public ResponseEntity<Map<String, String>> handleReceiptFileStorageException(ReceiptFileStorageException ex) {
    Map<String, String> error = Map.of("fileError", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(ResellerNotFoundException.class)
  public ResponseEntity<String> handleResellerNotFound(ResellerNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(ReceiptNotFoundException.class)
  public ResponseEntity<String> handleReceiptNotFoundException(ReceiptNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(ProductionValidationExption.class)
  public ResponseEntity<Map<String, String>> handleProductionValidationExption(ProductionValidationExption ex) {
    Map<String, String> error = Map.of("fileError", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(ProductionNotFoundException.class)
  public ResponseEntity<String> handleProductionNotFoundException(ProductionNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

}
