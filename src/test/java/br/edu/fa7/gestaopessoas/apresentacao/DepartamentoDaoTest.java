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

import java.util.List;

public class DepartamentoDaoTest {
    DepartamentoDao departamentoDao;
    private Session session;

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

    @Test
    public void deveriaSalvarVariosCargos() throws Exception {
        Departamento ti = new Departamento("TI");
        Departamento logistica = new Departamento("Logistica");
        Departamento comercial = new Departamento("Comercial");
        Departamento rh = new Departamento("RH");
        Departamento setorPessoal = new Departamento("Setor Pessoal");
        Departamento almoxarifado = new Departamento("Almoxarifado");

        departamentoDao.salvar(ti);
        departamentoDao.salvar(logistica);
        departamentoDao.salvar(comercial);
        departamentoDao.salvar(rh);
        departamentoDao.salvar(setorPessoal);
        departamentoDao.salvar(almoxarifado);

        List<Cargo> cargos = departamentoDao.listarTodos(Departamento.class);

        Assert.assertFalse(cargos.isEmpty(), "O resultado não deveria ser vazio");
    }

}
