package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.PessoaDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.*;
import br.edu.fa7.gestaopessoas.util.DataUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class PessoaTest {
    PessoaDao pessoaDao;
    private Session session;

    @Before
    public void setup() throws Exception {
        session = HibernateFactory.getHibernateSession();
        session.beginTransaction();
        pessoaDao = new PessoaDao();
    }

    @After
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


        Assert.assertNotNull("deveria existir id", pf1.getId());

        PessoaFisica pessoa = (PessoaFisica) pessoaDao.getPessoa(pf1.getId());

        Assert.assertEquals("nome", pf1.getName(), pessoa.getName());
        Assert.assertEquals("cargo", vinculo.getCargo().getNome(), pessoa.getVinculoAtual().getCargo().getNome());
        Assert.assertEquals("departamento", vinculo.getDepartamento().getNome(), pessoa.getVinculoAtual().getDepartamento().getNome());
//        Assert.assertEquals("quantidade de vinculos", 1, pf1.getVinculos().size());
    }

    @Test
    public void deveriaSalvarUmaPessoaJuridica() throws Exception {
        PessoaJuridica pj1 = new PessoaJuridica();

        pj1.setCnpj("35.507.574/0001-35");
        pj1.setName("Sandra Eva Transportes Intermunicipais LTDA.");
        pj1.setNomeFantasia("Sandra Eva Transportes");

        pessoaDao.salvar(pj1);

        Pessoa pessoa = pessoaDao.getPessoa(pj1.getId());
        Assert.assertEquals("nome", pj1.getName(), pessoa.getName());
    }
}
