package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.CargoDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
        System.out.println("deveria Cria O Cargo Para O Cache");
        CargoDao cargoDao = new CargoDao();
        Cargo cargo = new Cargo("Programador Junior", new BigDecimal(99999.99));
        cargoDao.salvar(cargo);
    }

    @Test(priority = 2)
    public void deveriaFazerAPrimeiraConsulta() throws Exception {
        System.out.println("deveria Fazer A Primeira Consulta");
        List<Cargo> result = session.createQuery(consulta).setParameter("nome", "%Programador%").setCacheable(true).list();
        System.out.println("Result = " + result.get(0).getNome());
    }

    @Test(priority = 3)
    public void deveriaConsultarNoCache() throws Exception {
        System.out.println("deveria Consultar No Cache");
        List<Cargo> result = session.createQuery(consulta).setParameter("nome", "%Programador%").setCacheable(true).list();
        System.out.println("Result id = " + result.get(0).getId());
        System.out.println("Result nome = " + result.get(0).getNome());
    }

    @Test(priority = 4)
    public void deveriaExibirONomeDoCargoAtual() throws Exception {
        System.out.println("deveria Exibir O Nome Do Cargo");
        Cargo cargo = session.get(Cargo.class, 1);
        System.out.println(cargo.getNome());
    }


    @Test(priority = 5)
    public void deveriaAlterarOCargoViaJDBC() throws Exception {
        System.out.println("deveria Alterar O Cargo Via JDBC");

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("update gestaopessoas.cargo set nome = 'Analista de Sistemas' where id = 1");
    }

    @Test(priority = 6)
    public void deveriaExibirONomeDoCargoAntigoDevidoOCacheDeSegundoNivel() throws Exception {
        System.out.println("deveria Exibir O Nome Do Cargo Antigo Devido O Cache De Segundo Nivel");
        Cargo cargo = session.get(Cargo.class, 1);
        System.out.println("Cache = " + cargo.getNome());
    }

}
