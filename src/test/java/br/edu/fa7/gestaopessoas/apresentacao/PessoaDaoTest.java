package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.PessoaDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.*;
import br.edu.fa7.gestaopessoas.util.DataUtil;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class PessoaDaoTest {
    PessoaDao pessoaDao;
    private Session session;

    @BeforeMethod
    public void setup() throws Exception {
        session = HibernateFactory.getHibernateSession();
        session.beginTransaction();
        pessoaDao = new PessoaDao();
    }

    @AfterMethod
    public void cleanup() {
        session.getTransaction().commit();
    }

    @Test
    public void deveriaSalvarUmaPessoaFisica() throws Exception {
        Cargo desenvolvedor = new Cargo();
        desenvolvedor.setNome("Desenvolvedor");
        desenvolvedor.setSalario(new BigDecimal(3000));
        pessoaDao.salvar(desenvolvedor);

        Departamento ti = new Departamento();
        ti.setNome("TI");
        pessoaDao.salvar(ti);


        PessoaFisica pf1 = new PessoaFisica();
        LocalDate dataNascimento = LocalDate.of(1990, Month.SEPTEMBER, 26);
        pf1.setName("Clairton");
        pf1.setCpf("111.222.333-22");
        pf1.setDataNascimento(DataUtil.toDate(dataNascimento));
        pessoaDao.salvar(pf1);

        Vinculo vinculo = new Vinculo();
        vinculo.setInicio(new Date());
        vinculo.setCargo(desenvolvedor);
        vinculo.setDepartamento(ti);
        vinculo.setPessoa(pf1);
        pessoaDao.salvar(vinculo);

        pf1.setVinculoAtual(vinculo);
        pessoaDao.salvar(pf1);


        Assert.assertNotNull(pf1.getId(), "deveria existir id");

        PessoaFisica pessoa = (PessoaFisica) pessoaDao.buscarPorId(pf1.getId());

        Assert.assertEquals("nome", pf1.getName(), pessoa.getName());
        Assert.assertEquals("cargo", vinculo.getCargo().getNome(), pessoa.getVinculoAtual().getCargo().getNome());
        Assert.assertEquals("departamento", vinculo.getDepartamento().getNome(), pessoa.getVinculoAtual().getDepartamento().getNome());
//        Assert.assertEquals("quantidade de vinculos", 1, pf1.getVinculos().size());
    }

    @Test
    public void deveriaSalvarUmaPessoaJuridica() throws Exception {
        Cargo cargo = new Cargo();
        cargo.setNome("Outsourcing Impressoras");
        cargo.setSalario(new BigDecimal(15000));
        pessoaDao.salvar(cargo);

        Departamento ti = new Departamento();
        ti.setNome("TI");
        pessoaDao.salvar(ti);


        PessoaJuridica pj1 = new PessoaJuridica();

        pj1.setCnpj("35.507.574/0001-35");
        pj1.setName("Sandra Eva Transportes Intermunicipais LTDA.");
        pj1.setNomeFantasia("Sandra Eva Transportes");

        pessoaDao.salvar(pj1);

        Vinculo vinculo = new Vinculo();
        vinculo.setInicio(new Date());
        vinculo.setCargo(cargo);
        vinculo.setDepartamento(ti);
        vinculo.setPessoa(pj1);
        pessoaDao.salvar(vinculo);

        pj1.setVinculoAtual(vinculo);
        pessoaDao.salvar(pj1);


        Assert.assertNotNull(pj1.getId(), "deveria existir id");

        PessoaJuridica pessoa = (PessoaJuridica) pessoaDao.buscarPorId(pj1.getId());

        Assert.assertEquals("nome", pj1.getName(), pessoa.getName());
        Assert.assertEquals("cargo", vinculo.getCargo().getNome(), pessoa.getVinculoAtual().getCargo().getNome());
        Assert.assertEquals("departamento", vinculo.getDepartamento().getNome(), pessoa.getVinculoAtual().getDepartamento().getNome());
//        Assert.assertEquals("quantidade de vinculos", 1, pf1.getVinculos().size());
    }
}
