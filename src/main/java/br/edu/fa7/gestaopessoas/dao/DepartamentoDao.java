package br.edu.fa7.gestaopessoas.dao;


import br.edu.fa7.gestaopessoas.models.Departamento;

import java.util.List;

public class DepartamentoDao extends GenericDao {

    public DepartamentoDao() throws Exception {
        super();
    }

    public Departamento buscarPorId(Integer id) throws Exception {
        String consulta = "from Departamento c where c.id = :id ";
        return (Departamento) super.session.createQuery(consulta).setParameter("id", id).uniqueResult();
    }

    public List<Departamento> buscarPorNome(String nome) {
        String consulta = "from Departamento d where d.nome = :nome";
        return (List<Departamento>) super.session.createQuery(consulta)
                .setParameter("nome", nome)
                .list();
    }
}
