package mdg.miguel.mdgproject.enums;

public enum PaymentMethod {

  DINHEIRO("Dinheiro"),
  PIX("Pix"),
  CARTAO("Cartão");

  private final String code;

  public String getCode() {
    return code;
  }

  private PaymentMethod(String code) {
    this.code = code;
  }

}
