package br.edu.fa7.gestaopessoas.dao;


import br.edu.fa7.gestaopessoas.models.Pessoa;

public class PessoaDao extends GenericDao {

    public PessoaDao() throws Exception {
        super();
    }

    public Pessoa buscarPorId(Integer id) throws Exception {
        String consulta = "from Pessoa p where p.id = :id ";
        return (Pessoa) super.session.createQuery(consulta).setParameter("id", id).uniqueResult();
    }

}
