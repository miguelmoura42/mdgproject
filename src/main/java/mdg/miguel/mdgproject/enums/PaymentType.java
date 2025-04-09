package mdg.miguel.mdgproject.enums;

public enum PaymentType {

  ENERGIA("Energia"),
  AGUA("Água"),
  COLABORADORES("Colaboradores"),
  FORNECEDORES("Fornecedores"),
  CARTOES("Cartões"),
  ALUGUEL("Aluguel");

  private final String code;

  public String getCode() {
    return code;
  }

  private PaymentType(String code) {
    this.code = code;
  }

}
