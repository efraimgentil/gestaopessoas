package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 17/03/16.
 */
public class InserirPessoaEVincularADepartamentoCargo {

  Session session;

  @BeforeMethod
  public void setUpTest() throws Exception {
    session = HibernateFactory.getHibernateSession();
    session.beginTransaction();
  }

  @AfterMethod
  public void finishTest(){
    session.getTransaction().commit();
  }

  @AfterClass
  public void destroyTest(){
    HibernateFactory.closeFactory();
  }

  @Test( priority = 1 , description = "Tentara salvar uma pessoa de nome Efraim Gentil")
  public void devePersistirAPessoaEfraim(){
    PessoaFisica p = new PessoaFisica();
    p.setName("Efraim Gentil");
    p.setCpf("11234421234");
    p.setDataNascimento(new Date());
    session.persist(p);
  }

  @Test( priority = 2 , description = "Garante que a pessoa de nome Efraim Gentil foi persistida")
  public void deveBuscarAPessoaEfraim(){
    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
    query.setParameter("nome", "EFRAIM%");
    List<Pessoa> resultList = query.list();

    assertFalse( resultList.isEmpty() );
    for( Pessoa p : resultList ){
      assertTrue( p.getName().toUpperCase().contains("EFRAIM") );
    }
  }

  @Test( priority = 3 , description = "Cadastra um cargo programador")
  public void devePersistirUmCargo(){
    Cargo cargo = new Cargo();
    cargo.setNome("Programador");
    cargo.setSalario(new BigDecimal(999999.999999));
    session.persist(cargo);
  }

  @Test( priority = 4 , description = "Cadastra um departamento")
  public void devePersistirUmDepartamento(){
    Departamento departamento = new Departamento();
    departamento.setNome("ASTIN");
    session.persist(departamento);
  }

  @Test( priority = 5 , description = "Deve criar um vinculo para a pessoa " +
          "Efraim de cargo Programador no departamento ASTIN")
  public void devePersistirUmVinculoParaAPessoa(){
    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
    query.setParameter("nome", "EFRAIM%");
    Pessoa pessoa  = (Pessoa) query.list().get(0);

    Query queryCargo = session.createQuery("FROM Cargo a WHERE upper(a.nome) like :nome");
    queryCargo.setParameter("nome", "PROGRAMADOR%");
    Cargo cargo  = (Cargo) queryCargo.list().get(0);

    Query queryDepartamento = session.createQuery("FROM Departamento a WHERE upper(a.nome) like :nome");
    queryDepartamento.setParameter("nome", "ASTIN%");
    Departamento departamento = (Departamento) queryDepartamento.list().get(0);

    pessoa.novoVinculo( departamento , cargo );

    session.merge(pessoa);
  }

  @Test( priority = 6 , description = "Deve consulta pessoa Efraim e garantir que vinculoAtual esteja preenchido")
  public void deveGarantirQueAPessoaPossuiVinculoAtual(){
    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
    query.setParameter("nome", "EFRAIM%");
    Pessoa pessoa  = (Pessoa) query.list().get(0);

    assertNotNull(pessoa.getVinculoAtual());
    assertEquals(pessoa.getVinculoAtual().getId(), pessoa.getVinculos().get(0).getId());
  }

  @Test( priority = 7 , description = "Deve fechar o vinculo atual da pessoa Efraim")
  public void deveGarantirQueAPessoaFoiAtualizadaJuntoComVinculo(){
    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
    query.setParameter("nome", "EFRAIM%");
    Pessoa pessoa  = (Pessoa) query.list().get(0);

    pessoa.fechaVinculoAtual();

    session.merge( pessoa );
  }

  @Test( priority = 8 , description = "Deve deve garantir que o vinculo da pessoa efraim foi fechado")
  public void deveGarantirQueAPessoaEfraimTeveSeuVinculoFechado(){
    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
    query.setParameter("nome", "EFRAIM%");
    Pessoa pessoa  = (Pessoa) query.list().get(0);

    assertNull( pessoa.getVinculoAtual() );
    for(Vinculo vinculo : pessoa.getVinculos()){
      assertNotNull( vinculo.getFim() );
    }
  }

}
