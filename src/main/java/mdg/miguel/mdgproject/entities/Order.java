package mdg.miguel.mdgproject.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import mdg.miguel.mdgproject.enums.OrderStatus;

@Entity
@Table(name = "tb_pedidos")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long quantity;
  private Long amount;
  private LocalDate date;
  @Enumerated(EnumType.STRING)
  private OrderStatus status;
  @Column(unique = true, nullable = false)
  private String orderCode;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reseller_id")
  private Reseller reseller;

  public Order() {
  }

  public Order(Long id, Long quantity, Long amount, LocalDate date, OrderStatus status, String orderCode,
      Reseller reseller) {
    this.id = id;
    this.quantity = quantity;
    this.amount = amount;
    this.date = date;
    this.status = status;
    this.orderCode = orderCode;
    this.reseller = reseller;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Reseller getReseller() {
    return reseller;
  }

  public void setReseller(Reseller reseller) {
    this.reseller = reseller;
  }

  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

}
