package mdg.miguel.mdgproject.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_producao")
public class DailyProduction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long morningProduction;
  private Long afternoonProduction;
  @Column(unique = true)
  private LocalDate date;

  public DailyProduction() {
  }

  public DailyProduction(Long id, Long morningProduction, Long afternoonProduction,
      LocalDate date) {
    this.id = id;
    this.morningProduction = morningProduction;
    this.afternoonProduction = afternoonProduction;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
