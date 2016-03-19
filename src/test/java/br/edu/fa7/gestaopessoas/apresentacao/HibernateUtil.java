//package br.edu.fa7.gestaopessoas.apresentacao;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
///**
// * Created by efraimgentil<efraimgentil@gmail.com> on 17/03/16.
// */
//public class HibernateUtil {
//
//  private static SessionFactory sessionFactory;
//
//
//  public static SessionFactory getSessionFactory(){
//    if(sessionFactory == null || sessionFactory.isClosed() ){
//      try {
//        setUp();
//      } catch (Exception e) {
//        throw new RuntimeException( e );
//      }
//    }
//    return sessionFactory;
//  }
//
//
//  protected static void setUp() throws Exception {
//    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//            .configure() // configures settings from hibernate.cfg.xml
//            .build();
//    try {
//      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//    }
//    catch (Exception e) {
//      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//      // so destroy it manually.
//      StandardServiceRegistryBuilder.destroy( registry );
//    }
//  }
//
//}
