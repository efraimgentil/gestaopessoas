//package br.edu.fa7.gestaopessoas.apresentacao;
//
//import br.edu.fa7.gestaopessoas.models.Pessoa;
//import br.edu.fa7.gestaopessoas.models.PessoaFisica;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.test.annotation.Rollback;
//import org.testng.annotations.*;
//
//import java.util.List;
//
//import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
///**
// * Created by efraimgentil<efraimgentil@gmail.com> on 17/03/16.
// */
//public class InserirPessoaEVincular {
//
//  SessionFactory sessionFactory;
//  Session session;
//
//  @BeforeClass
//  public void setUp(){
//    sessionFactory = HibernateUtil.getSessionFactory();
//  }
//  @AfterClass
//  public void destroy(){
//    sessionFactory.close();
//  }
//
//  @BeforeMethod
//  public void setUpTest(){
//    session = sessionFactory.openSession();
//  }
//
//  @AfterMethod
//  public void destroyTest(){
//    session.close();
//  }
//
//  @Test( priority = 1 , description = "Tentara salvar uma pessoa de nome Efraim Gentil")
//  public void devePersistirAPessoaEfraim(){
//    session.getTransaction().begin();
//    Pessoa p = new PessoaFisica();
//    p.setName("Efraim Gentil");
//    session.persist(p);
//    session.getTransaction().commit();
//  }
//
//  @Test( priority = 2 , description = "Garante que a pessoa de nome Efraim Gentil foi persistida")
//  public void deveBuscarAPessoaEfraim(){
//    Query query = session.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome");
//    query.setParameter("nome", "EFRAIM%");
//    List<Pessoa> resultList = query.list();
//
//    assertFalse( resultList.isEmpty() );
//    for( Pessoa p : resultList ){
//      assertTrue( p.getName().toUpperCase().contains("EFRAIM") );
//    }
//  }
///*
//  @Rollback(false)
//  @Test( priority = 3 , description = "Cadastra um cargo programador")
//  public void devePersistirUmCargo(){
//    Cargo cargo = new Cargo();
//    cargo.setNome("Programador");
//    cargo.setSalario( new BigDecimal(999999.999999));
//    entityManager.persist( cargo );
//  }
//
//  @Rollback(false)
//  @Test( priority = 4 , description = "Cadastra um departamento")
//  public void devePersistirUmDepartamento(){
//    Departamento departamento = new Departamento();
//    departamento.setNome("ASTIN");
//    entityManager.persist( departamento );
//  }
//
//  @Rollback(false)
//  @Test( priority = 5 , description = "Deve criar um vinculo para a pessoa " +
//          "Efraim de cargo Programador no departamento ASTIN")
//  public void devePersistirUmVinculoParaAPessoa(){
//    TypedQuery<Pessoa> query = entityManager.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome", Pessoa.class);
//    query.setParameter("nome", "EFRAIM%");
//    Pessoa pessoa  = query.getSingleResult();
//
//    TypedQuery<Cargo> queryCargo = entityManager.createQuery("FROM Cargo a WHERE upper(a.nome) like :nome", Cargo.class);
//    queryCargo.setParameter("nome", "PROGRAMADOR%");
//    Cargo cargo  = queryCargo.getSingleResult();
//
//    TypedQuery<Departamento> queryDepartamento = entityManager.createQuery("FROM Departamento a WHERE upper(a.nome) like :nome", Departamento.class);
//    queryDepartamento.setParameter("nome", "ASTIN%");
//    Departamento departamento = queryDepartamento.getSingleResult();
//
//    pessoa.novoVinculo( departamento , cargo );
//
//    entityManager.merge(pessoa);
//  }
//
//  @Test( priority = 6 , description = "Deve consulta pessoa Efraim e garantir que vinculoAtual esteja preenchido")
//  public void deveGarantirQueAPessoaPossuiVinculoAtual(){
//    TypedQuery<Pessoa> query = entityManager.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome", Pessoa.class);
//    query.setParameter("nome", "EFRAIM%");
//    Pessoa pessoa  = query.getSingleResult();
//
//    assertNotNull(pessoa.getVinculoAtual());
//    assertEquals(pessoa.getVinculoAtual().getId(), pessoa.getVinculos().get(0).getId());
//  }
//
//  @Rollback(false)
//  @Test( priority = 7 , description = "Deve fechar o vinculo atual da pessoa Efraim")
//  public void deveGarantirQueAPessoaFoiAtualizadaJuntoComVinculo(){
//    TypedQuery<Pessoa> query = entityManager.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome", Pessoa.class);
//    query.setParameter("nome", "EFRAIM%");
//    Pessoa pessoa  = query.getSingleResult();
//
//    pessoa.fechaVinculoAtual();
//
//    entityManager.merge( pessoa );
//  }
//
//  @Test( priority = 8 , description = "Deve deve garantir que o vinculo da pessoa efraim foi fechado")
//  public void deveGarantirQueAPessoaEfraimTeveSeuVinculoFechado(){
//    TypedQuery<Pessoa> query = entityManager.createQuery("FROM Pessoa a WHERE upper(a.name) like :nome", Pessoa.class);
//    query.setParameter("nome", "EFRAIM%");
//    Pessoa pessoa  = query.getSingleResult();
//
//    assertNull( pessoa.getVinculoAtual() );
//    for(Vinculo vinculo : pessoa.getVinculos()){
//      assertNotNull( vinculo.getFim() );
//    }
//  }*/
//
//}
