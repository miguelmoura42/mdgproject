package mdg.miguel.mdgproject.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import mdg.miguel.mdgproject.enums.PaymentMethod;
import mdg.miguel.mdgproject.enums.PaymentType;

@Entity
@Table(name = "tb_receipts")
public class PaymentReceipt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;

  @Enumerated(EnumType.STRING)
  private PaymentType paymentType;
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private LocalDate paymentDate;
  private String paymentAmount;
  private String receiptFilePath;
  @Column(unique = true, nullable = false)
  private UUID uuid = UUID.randomUUID();

  public PaymentReceipt() {
  }

  public PaymentReceipt(Long id, String description, PaymentType paymentType, PaymentMethod paymentMethod,
      LocalDate paymentDate, String paymentAmount, String receiptFilePath, UUID uuid) {
    this.id = id;
    this.description = description;
    this.paymentType = paymentType;
    this.paymentMethod = paymentMethod;
    this.paymentDate = paymentDate;
    this.paymentAmount = paymentAmount;
    this.receiptFilePath = receiptFilePath;
    this.uuid = uuid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getReceiptFilePath() {
    return receiptFilePath;
  }

  public void setReceiptFilePath(String receiptFilePath) {
    this.receiptFilePath = receiptFilePath;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PaymentReceipt other = (PaymentReceipt) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
