package mdg.miguel.mdgproject.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import mdg.miguel.mdgproject.enums.Cities;

@Entity
@Table(name = "tb_revendedores")
public class Reseller {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String name;
  @Enumerated(EnumType.STRING)
  private Cities city;
  @Column(unique = true)
  private String uniqueKey;

  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "reseller", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Order> orders = new HashSet<>();

  public Reseller() {
  }

  public Reseller(Long id, String name, Cities city, String uniqueKey) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.uniqueKey = uniqueKey;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Set<Order> getOrders() {
    return orders;
  }

  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

}
