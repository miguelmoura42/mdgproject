package mdg.miguel.mdgproject.enums;

public enum Cities {

  PICOS("Picos"),
  FLORIANO("Floriano"),
  PETROLINA("Petrolina"),
  GEMINIANO("Geminiano"),
  MONSENHOR("Monsenhor Hipólito"),
  DEL("Dom Expedito"),
  PAULISTANA("Paulistana");

  private final String code;

  public String getCode() {
    return code;
  }

  private Cities(String code) {
    this.code = code;
  }

}
