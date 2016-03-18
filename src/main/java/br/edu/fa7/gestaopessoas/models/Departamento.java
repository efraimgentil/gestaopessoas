package br.edu.fa7.gestaopessoas.models;

import javax.persistence.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 14/03/16.
 */
@Entity
@Table(name="departamento")
public class Departamento {

  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;
  @Column(name="nome" , nullable=false)
  private String nome;

  public Departamento() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Departamento that = (Departamento) o;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
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
}
