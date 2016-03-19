package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.CargoDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;

public class CargoTest {
    CargoDao cargoDao;
    private Session session;

    @Before
    public void setup() throws Exception {
        session = HibernateFactory.getHibernateSession();
        session.beginTransaction();
        cargoDao = new CargoDao();
    }

    @After
    public void cleanup() {
        session.getTransaction().commit();
    }

    @Test
    public void deveriaSalvarVariosCargos() throws Exception {
        Cargo desenvolvedor = new Cargo("Desenvolvedor", new BigDecimal(3000));
        Cargo analista = new Cargo("Analista de Sistemas", new BigDecimal(5000));
        Cargo arquiteto = new Cargo("Arquiteto de Software", new BigDecimal(5000));
        Cargo engenheiro = new Cargo("Engenheiro de Software", new BigDecimal(5000));
        Cargo qualidade = new Cargo("Analista de Qualidade", new BigDecimal(5000));
        Cargo gerente = new Cargo("Gerente de TI", new BigDecimal(5000));

        cargoDao.salvar(desenvolvedor);
        cargoDao.salvar(analista);
        cargoDao.salvar(arquiteto);
        cargoDao.salvar(engenheiro);
        cargoDao.salvar(qualidade);
        cargoDao.salvar(gerente);

        List<Cargo> cargos = cargoDao.listarTodos(Cargo.class);

        Assert.assertFalse(cargos.isEmpty(), "O resultado não deveria ser vazio");
    }

}
