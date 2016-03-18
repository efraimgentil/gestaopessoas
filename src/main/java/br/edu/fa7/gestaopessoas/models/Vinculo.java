package br.edu.fa7.gestaopessoas.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 14/03/16.
 */
@Entity
@Table(name="vinculo")
public class Vinculo {

  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name="pessoa_id" , nullable =  false)
  private Pessoa pessoa;

  @ManyToOne
  @JoinColumn(name="cargo_id" , nullable =  false)
  private Cargo cargo;

  @ManyToOne
  @JoinColumn(name="departamento_id" , nullable =  false)
  private Departamento departamento;


  @Temporal(TemporalType.DATE)
  @Column(name="inicio" , nullable = false)
  private Date inicio;
  @Temporal(TemporalType.DATE)
  @Column(name="fim" , nullable = true)
  private Date fim;

  public Vinculo() {
  }

  public Vinculo(Pessoa pessoa, Cargo cargo, Departamento departamento) {
    this.pessoa = pessoa;
    this.cargo = cargo;
    this.departamento = departamento;
    this.inicio = new Date();
  }

  public void finalizaVinculo() {
    fim = new Date();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vinculo vinculo = (Vinculo) o;
    if (id != null ? !id.equals(vinculo.id) : vinculo.id != null) return false;
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

  public Pessoa getPessoa() {
    return pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public Departamento getDepartamento() {
    return departamento;
  }

  public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
  }

  public Date getInicio() {
    return inicio;
  }

  public void setInicio(Date inicio) {
    this.inicio = inicio;
  }

  public Date getFim() {
    return fim;
  }

  public void setFim(Date fim) {
    this.fim = fim;
  }


}
