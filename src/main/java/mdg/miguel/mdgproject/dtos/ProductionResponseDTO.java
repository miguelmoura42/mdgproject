package mdg.miguel.mdgproject.dtos;

import java.time.LocalDate;

public class ProductionResponseDTO {

  private Long morningProduction;
  private Long afternoonProduction;
  private Long totalProduction;
  private LocalDate date;

  public ProductionResponseDTO() {
  }

  public ProductionResponseDTO(Long morningProduction, Long afternoonProduction, Long totalProduction, LocalDate date) {
    this.morningProduction = morningProduction;
    this.afternoonProduction = afternoonProduction;
    this.totalProduction = totalProduction;
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

  public Long getTotalProduction() {
    return totalProduction;
  }

  public void setTotalProduction(Long totalProduction) {
    this.totalProduction = totalProduction;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

}
