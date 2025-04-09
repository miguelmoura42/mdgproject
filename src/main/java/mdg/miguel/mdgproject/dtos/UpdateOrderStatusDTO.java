package mdg.miguel.mdgproject.dtos;

import mdg.miguel.mdgproject.enums.OrderStatus;

public class UpdateOrderStatusDTO {

  private String orderCode;
  private OrderStatus newStatus;

  public UpdateOrderStatusDTO() {
  }

  public UpdateOrderStatusDTO(String orderCode, OrderStatus newStatus) {
    this.orderCode = orderCode;
    this.newStatus = newStatus;
  }

  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

  public OrderStatus getNewStatus() {
    return newStatus;
  }

  public void setNewStatus(OrderStatus newStatus) {
    this.newStatus = newStatus;
  }

}
