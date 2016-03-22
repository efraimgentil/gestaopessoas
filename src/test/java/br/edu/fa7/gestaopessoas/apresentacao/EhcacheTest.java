package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.CargoDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class EhcacheTest {
    private Session session;
    private String consulta = "from Cargo c where c.nome like :nome ";

    @BeforeMethod
    public void setup() throws Exception {
        session = HibernateFactory.getHibernateSession();
        session.beginTransaction();

    }

    @AfterMethod
    public void cleanup() {
        session.getTransaction().commit();
    }

    @Test(priority = 1)
    public void deveriaCriaOCargoParaOCache() throws Exception {
        CargoDao cargoDao = new CargoDao();
        Cargo cargo = new Cargo("Programador Junior", new BigDecimal(99999.99));
        cargoDao.salvar(cargo);
    }

    @Test(priority = 2)
    public void deveriaFazerAPrimeiraConsulta() throws Exception {
        System.out.println("deveriaFazerAPrimeiraConsulta");
        List<Cargo> result = session.createQuery(consulta).setParameter("nome", "%Programador%").setCacheable(true).list();

        System.out.println("Result = " + result.get(0).getNome());
        System.out.println("deveriaFazerAPrimeiraConsulta END");
    }

    @Test(priority = 3)
    public void deveriaConsultarNoCache() throws Exception {
        System.out.println("deveriaConsultarNoCache");
        List<Cargo> result = session.createQuery(consulta).setParameter("nome", "%Programador%").setCacheable(true).list();
        System.out.println("Result = " + result.get(0).getNome());
        System.out.println("Result = " + result.get(0).getId());
        System.out.println("deveriaConsultarNoCache END");
    }

    @Test(priority = 4)
    public void deveriaAlterarOValorNoBanco() throws Exception {
        System.out.println("deveriaAlterarOValorNoBanco");

        String consulta = "from Cargo c where c.nome like :nome ";
        List<Cargo> result = session.createQuery(consulta).setParameter("nome", "%Programador%").setCacheable(true).list();
        Cargo cargo = result.get(0);

        cargo.setNome("Analista de Sistemas");
        CargoDao cargoDao = new CargoDao();
        cargoDao.salvar(cargo);

        System.out.println("deveriaAlterarOValorNoBanco END");
    }


    @Test(priority = 5)
    public void deveriaConsultarNoCacheENaoMostrarAlteracao() throws Exception {
        System.out.println("deveriaConsultarNoCacheENaoMostrarAlteracao");
        Cargo result = session.get(Cargo.class, 1);
        System.out.println("Result = " + result.getNome());
        System.out.println("deveriaConsultarNoCacheENaoMostrarAlteracao END");
    }
}
