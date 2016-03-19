package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.dao.PessoaDao;
import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.Pessoa;
import br.edu.fa7.gestaopessoas.models.PessoaFisica;
import br.edu.fa7.gestaopessoas.models.PessoaJuridica;
import br.edu.fa7.gestaopessoas.util.DataUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

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
        PessoaFisica pf1 = new PessoaFisica();
        pf1.setName("Clairton");

        LocalDate dataNascimento = LocalDate.of(1990, Month.SEPTEMBER, 26);
        pf1.setCpf("111.222.333-22");
        pf1.setDataNascimento(DataUtil.toDate(dataNascimento));

        pessoaDao.salvar(pf1);

        Pessoa pessoa = pessoaDao.getPessoa(pf1.getId());
        Assert.assertEquals("nome", pf1.getName(), pessoa.getName());
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
