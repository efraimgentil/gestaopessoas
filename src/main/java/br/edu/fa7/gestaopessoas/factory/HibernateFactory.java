package br.edu.fa7.gestaopessoas.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static SessionFactory sf;

    public static synchronized Session getHibernateSession() throws Exception {
        if (sf == null) {
            sf = new Configuration().configure().buildSessionFactory();
        }
        Session s = sf.getCurrentSession();
        return s;
    }

    public static synchronized void closeFactory(){
        if(!sf.isClosed()) {
            sf.close();
        }
    }

}
