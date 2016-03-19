package br.edu.fa7.gestaopessoas.dao;


import br.edu.fa7.gestaopessoas.models.Cargo;

import java.util.List;

public class CargoDao extends GenericDao {

    public CargoDao() throws Exception {
        super();
    }

    public Cargo buscarPorId(Integer id) throws Exception {
        String consulta = "from Cargo c where c.id = :id ";
        return (Cargo) super.session.createQuery(consulta).setParameter("id", id).uniqueResult();
    }

    public List<Cargo> buscarPorNome(String nome) {
        String consulta = "from Cargo c where c.nome = :nome";
        return (List<Cargo>) super.session.createQuery(consulta)
                .setParameter("nome", nome)
                .list();
    }
}
