package mdg.miguel.mdgproject.dtos;

import java.time.LocalDate;
import java.util.UUID;

import mdg.miguel.mdgproject.enums.PaymentMethod;
import mdg.miguel.mdgproject.enums.PaymentType;

public class ReceiptResponseDTO {
  private String description;
  private PaymentType paymentType;
  private PaymentMethod paymentMethod;
  private LocalDate paymentDate;
  private String paymentAmount;
  private UUID uuid;

  public ReceiptResponseDTO() {
  }

  public ReceiptResponseDTO(String description, PaymentType paymentType, PaymentMethod paymentMethod,
      LocalDate paymentDate, String paymentAmount, UUID uuid) {
    this.description = description;
    this.paymentType = paymentType;
    this.paymentMethod = paymentMethod;
    this.paymentDate = paymentDate;
    this.paymentAmount = paymentAmount;
    this.uuid = uuid;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }

  public String getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(String paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

}
