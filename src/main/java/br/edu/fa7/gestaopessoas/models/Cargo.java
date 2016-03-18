package br.edu.fa7.gestaopessoas.models;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 14/03/16.
 */
@Entity
@Table(name="cargo")
public class Cargo {

  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="nome")
  private String nome;

  @Column(nullable = false , scale = 4)
  private BigDecimal salario;

  public Cargo(){}

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Cargo{");
    sb.append("id=").append(id);
    sb.append(", nome='").append(nome).append('\'');
    sb.append(", salario=").append(salario);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cargo cargo = (Cargo) o;
    if (id != null ? !id.equals(cargo.id) : cargo.id != null) return false;
    return true;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public BigDecimal getSalario() {
    return salario;
  }
  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

}
