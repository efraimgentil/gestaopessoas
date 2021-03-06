package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.DepartamentoDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Cargo;
import br.edu.fa7.gestaopessoas.models.Departamento;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class DepartamentoDaoTest {
    DepartamentoDao departamentoDao;
    private Session session;
    private int totalDepartamento;

    @BeforeMethod
    public void setup() throws Exception {
        session = HibernateFactory.getHibernateSession();
        session.beginTransaction();
        departamentoDao = new DepartamentoDao();
    }

    @AfterMethod
    public void cleanup() {
        session.getTransaction().commit();
    }

    @Test(priority = 1)
    public void deveriaSalvarVariosDepartamentos() throws Exception {
        Cargo gerente = new Cargo("Gerente", new BigDecimal(12000));
        departamentoDao.salvar(gerente);

        Departamento ti = new Departamento("TI");
        Departamento logistica = new Departamento("Logistica");
        Departamento comercial = new Departamento("Comercial");
        Departamento rh = new Departamento("RH");
        Departamento setorPessoal = new Departamento("Setor Pessoal");
        Departamento almoxarifado = new Departamento("Almoxarifado");

        ti.addCargo(gerente);
        logistica.addCargo(gerente);
        comercial.addCargo(gerente);
        rh.addCargo(gerente);
        setorPessoal.addCargo(gerente);
        almoxarifado.addCargo(gerente);

        departamentoDao.salvar(ti);
        departamentoDao.salvar(logistica);
        departamentoDao.salvar(comercial);
        departamentoDao.salvar(rh);
        departamentoDao.salvar(setorPessoal);
        departamentoDao.salvar(almoxarifado);
    }

    @Test(priority = 2)
    public void deveriaExistirVariosDepartamentos() throws Exception {

        List<Departamento> departamentos = departamentoDao.listarTodos(Departamento.class);

        Assert.assertFalse(departamentos.isEmpty(), "O resultado não deveria ser vazio");
        totalDepartamento = departamentos.size();
    }


    @Test(priority = 3)
    public void deveriaRemoverUmDepartamento() throws Exception {
        List<Departamento> departamentos = departamentoDao.buscarPorNome("Almoxarifado");

        Assert.assertFalse(departamentos.isEmpty(), "O resultado não deveria está vázio");
        departamentoDao.deletar(departamentos.get(0));

        departamentos = departamentoDao.buscarPorNome("Analista de Qualidade");

        Assert.assertTrue(departamentos.isEmpty(), "O resultado deveria está vázio");
    }


    @Test(priority = 4)
    public void deveriaVerificarSeFoiRemovido() throws Exception {

        List<Departamento> departamentos = departamentoDao.listarTodos(Departamento.class);

        Assert.assertFalse(departamentos.isEmpty(), "O resultado não deveria ser vazio");
        Assert.assertEquals(totalDepartamento - 1, departamentos.size(), "Total de departamentos");
    }

}
