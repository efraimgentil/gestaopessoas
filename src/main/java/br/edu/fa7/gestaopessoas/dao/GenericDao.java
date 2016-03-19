package br.edu.fa7.gestaopessoas.dao;

import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.BaseModel;
import org.hibernate.Session;

import java.util.List;

public abstract class GenericDao {

    protected final Session session;

    public GenericDao() throws Exception {
        this.session = HibernateFactory.getHibernateSession();
    }

    public void salvar(BaseModel bm) {
        session.saveOrUpdate(bm);
    }

    public void deletar(BaseModel bm) {
        session.delete(bm);
    }

    public List<BaseModel> listarTodos(Class bm) {
        return session.createQuery("from " + bm.getName() + " e where e.estaAtivo = true").list();
    }

    public List<BaseModel> listarTodosComLimite(Class bm, Integer limite) {
        return session.createQuery("from " + bm.getName() + " e where e.estaAtivo = true").setMaxResults(limite).list();
    }

}
