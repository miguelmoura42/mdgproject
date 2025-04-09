package mdg.miguel.mdgproject.dtos;

import java.time.LocalDate;

import mdg.miguel.mdgproject.enums.OrderStatus;

public class OrderDTO {

  private Long quantity;
  private Long amount;
  private LocalDate date;
  private OrderStatus status;
  private String uniqueKey;

  public OrderDTO() {
  }

  public OrderDTO(Long quantity, Long amount, LocalDate date, OrderStatus status, String uniqueKey) {
    this.quantity = quantity;
    this.amount = amount;
    this.date = date;
    this.status = status;
    this.uniqueKey = uniqueKey;
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

  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

}
