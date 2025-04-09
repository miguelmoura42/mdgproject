package mdg.miguel.mdgproject.enums;

public enum OrderStatus {

  PAGO("Pago"),
  PENDENTE("Pendente"),
  PARCIAL("Parcialmente pago");

  private final String code;

  public String getCode() {
    return code;
  }

  private OrderStatus(String code) {
    this.code = code;
  }

}
