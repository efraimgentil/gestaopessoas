package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.CargoDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class CargoDaoTest {
    CargoDao cargoDao;
    private Session session;
    private int totalDeCargos;

    @BeforeMethod
    public void setup() throws Exception {
        session = HibernateFactory.getHibernateSession();
        session.beginTransaction();
        cargoDao = new CargoDao();
    }

    @AfterMethod
    public void cleanup() {
        session.getTransaction().commit();
    }

    @Test(priority = 1)
    public void deveriaSalvarVariosCargos() throws Exception {
        Cargo desenvolvedor = new Cargo("Desenvolvedor", new BigDecimal(3000));
        Cargo analista = new Cargo("Analista de Sistemas", new BigDecimal(5000));
        Cargo arquiteto = new Cargo("Arquiteto de Software", new BigDecimal(7000));
        Cargo engenheiro = new Cargo("Engenheiro de Software", new BigDecimal(6500));
        Cargo qualidade = new Cargo("Analista de Qualidade", new BigDecimal(4000));
        Cargo gerente = new Cargo("Gerente de TI", new BigDecimal(12000));

        cargoDao.salvar(desenvolvedor);
        cargoDao.salvar(analista);
        cargoDao.salvar(arquiteto);
        cargoDao.salvar(engenheiro);
        cargoDao.salvar(qualidade);
        cargoDao.salvar(gerente);
    }

    @Test(priority = 2)
    public void deveriaExistirVariosCargos() throws Exception {

        List<Cargo> cargos = cargoDao.listarTodos(Cargo.class);

        Assert.assertFalse(cargos.isEmpty(), "O resultado não deveria ser vazio");
        totalDeCargos = cargos.size();
    }

    @Test(priority = 3)
    public void deveriaRemoverUmCargo() throws Exception {
        List<Cargo> cargos = cargoDao.buscarPorNome("Analista de Qualidade");

        Assert.assertFalse(cargos.isEmpty(), "O resultado não deveria está vázio");
        cargoDao.deletar(cargos.get(0));

        cargos = cargoDao.buscarPorNome("Analista de Qualidade");

        Assert.assertTrue(cargos.isEmpty(), "O resultado deveria está vázio");
    }

    @Test(priority = 4)
    public void deveriaVerificarSeFoiRemovido() throws Exception {

        List<Cargo> cargos = cargoDao.listarTodos(Cargo.class);

        Assert.assertFalse(cargos.isEmpty(), "O resultado não deveria ser vazio");
        Assert.assertEquals(totalDeCargos - 1, cargos.size(), "Total de cargos");
    }

}
