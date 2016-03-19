package br.edu.fa7.gestaopessoas.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 12/03/16.
 */
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "vinculo_id")
    private Vinculo vinculoAtual;

    @OneToMany(mappedBy = "pessoa", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Vinculo> vinculos = new ArrayList<>();

    public Pessoa() {
    }

    public void novoVinculo(Departamento departamento, Cargo cargo) {
        fechaVinculoAtual();
        vinculoAtual = new Vinculo(this, cargo, departamento);
        vinculos.add(vinculoAtual);
    }

    public void fechaVinculoAtual() {
        int indexOf = getVinculos().indexOf(vinculoAtual);
        if (indexOf >= 0) {
            getVinculos().get(indexOf).finalizaVinculo();
        }
        vinculoAtual = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vinculo getVinculoAtual() {
        return vinculoAtual;
    }

    public void setVinculoAtual(Vinculo vinculoAtual) {
        this.vinculoAtual = vinculoAtual;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }

}
