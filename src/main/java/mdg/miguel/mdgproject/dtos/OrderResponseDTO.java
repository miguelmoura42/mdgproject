package mdg.miguel.mdgproject.dtos;

import java.time.LocalDate;

import mdg.miguel.mdgproject.enums.OrderStatus;

public class OrderResponseDTO {

  private Long quantity;
  private Long amount;
  private LocalDate date;
  private OrderStatus status;
  private String orderCode;

  public OrderResponseDTO() {
  }

  public OrderResponseDTO(Long quantity, Long amount, LocalDate date, OrderStatus status, String orderCode) {
    this.quantity = quantity;
    this.amount = amount;
    this.date = date;
    this.status = status;
    this.orderCode = orderCode;
  }

  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

}
