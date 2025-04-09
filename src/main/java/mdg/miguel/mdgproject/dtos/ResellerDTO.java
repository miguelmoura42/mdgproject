package mdg.miguel.mdgproject.dtos;

import mdg.miguel.mdgproject.enums.Cities;

public class ResellerDTO {

  private String name;
  private Cities city;
  private String uniqueKey;

  public ResellerDTO() {
  }

  public ResellerDTO(String name, Cities city, String uniqueKey) {
    this.name = name;
    this.city = city;
    this.uniqueKey = uniqueKey;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Cities getCity() {
    return city;
  }

  public void setCity(Cities city) {
    this.city = city;
  }

  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

}
