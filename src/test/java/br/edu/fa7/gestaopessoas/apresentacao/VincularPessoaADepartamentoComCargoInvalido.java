package br.edu.fa7.gestaopessoas.apresentacao;

import br.edu.fa7.gestaopessoas.factory.HibernateFactory;
import br.edu.fa7.gestaopessoas.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 17/03/16.
 */
public class VincularPessoaADepartamentoComCargoInvalido {

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

  @Test( priority = 3 , description = "Cadastra um cargo programador e anaslita de RH")
  public void devePersistirCargos(){
    Cargo cargo = new Cargo();
    cargo.setNome("Programador");
    cargo.setSalario(new BigDecimal(999999.999999));
    session.persist(cargo);
    cargo = new Cargo();
    cargo.setNome("Analista de RH");
    cargo.setSalario(new BigDecimal(999999.999999));
    session.persist(cargo);
  }

  @Test( priority = 4 , description = "Cadastra um departamento que pode comportar cargo de programador")
  public void devePersistirUmDepartamento(){
    Departamento departamento = new Departamento();
    departamento.setNome("ASTIN");
    Query query = session.createQuery("FROM Cargo a WHERE upper(a.nome) like :nome");
    query.setParameter("nome" , "PROGRAMADOR");
    departamento.adicionarCargo((Cargo) query.list().get(0));
    session.persist(departamento);
  }

  @Test( priority = 5 , description = "Deve tentar criar um vinculo para a pessoa " +
          "Efraim de cargo Analista de RH no departamento ASTIN" +
          ", e como o departamento não aceita o cargo deve lançar um PersistenceException"
          , expectedExceptions = { PersistenceException.class } )
  public void devePersistirUmVinculoParaAPessoa(){
    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
    query.setParameter("nome", "EFRAIM%");
    Pessoa pessoa  = (Pessoa) query.list().get(0);

    Query queryCargo = session.createQuery("FROM Cargo a WHERE upper(a.nome) like :nome");
    queryCargo.setParameter("nome", "Analista de RH%".toUpperCase() );
    Cargo cargo  = (Cargo) queryCargo.list().get(0);

    Query queryDepartamento = session.createQuery("FROM Departamento a WHERE upper(a.nome) like :nome");
    queryDepartamento.setParameter("nome", "ASTIN%");
    Departamento departamento = (Departamento) queryDepartamento.list().get(0);

    pessoa.novoVinculo( departamento , cargo );

    session.merge(pessoa);
  }

}
