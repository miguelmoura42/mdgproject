package mdg.miguel.mdgproject.dtos;

import java.time.LocalDate;

public class ProductionDTO {

  private Long morningProduction;
  private Long afternoonProduction;
  private LocalDate date;

  public ProductionDTO() {
  }

  public ProductionDTO(Long morningProduction, Long afternoonProduction, LocalDate date) {
    this.morningProduction = morningProduction;
    this.afternoonProduction = afternoonProduction;
    this.date = date;
  }

  public Long getMorningProduction() {
    return morningProduction;
  }

  public void setMorningProduction(Long morningProduction) {
    this.morningProduction = morningProduction;
  }

  public Long getAfternoonProduction() {
    return afternoonProduction;
  }

  public void setAfternoonProduction(Long afternoonProduction) {
    this.afternoonProduction = afternoonProduction;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

}
