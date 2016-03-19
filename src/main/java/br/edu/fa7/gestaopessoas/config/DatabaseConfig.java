package br.edu.fa7.gestaopessoas.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 08/03/16.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

  @Bean
  @Qualifier(value = "database")
  public ResourceBundle dataBaseProperties(){
    return ResourceBundle.getBundle("database");
  }

  @Qualifier(value = "postgres")
  @Bean
  public DataSource postgres(){
    ResourceBundle databaseBundle = dataBaseProperties();
    HikariConfig dataSourceConfig = new HikariConfig();
    dataSourceConfig.setDriverClassName(databaseBundle.getString("pg.driver"));
    dataSourceConfig.setJdbcUrl(databaseBundle.getString("pg.url"));
    dataSourceConfig.setUsername(databaseBundle.getString("pg.username"));
    dataSourceConfig.setPassword( databaseBundle.getString("pg.password")  );
    dataSourceConfig.setMaximumPoolSize( 10 );
    dataSourceConfig.setConnectionTestQuery( databaseBundle.getString("pg.testQuery") );
    dataSourceConfig.setIdleTimeout( 1000000 );
    return new HikariDataSource(dataSourceConfig);
  }

  @Bean(name = "postgresSessionFactory" )
  @DependsOn(value = "postgres")
  public LocalSessionFactoryBean entityManagerFactory(@Qualifier("postgres") DataSource dataSource) {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setPackagesToScan(new String[] { "br.edu.fa7.gestaopessoas.models" });

    Properties hibernateProperties = new Properties();
    hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    hibernateProperties.put("hibernate.show_sql", "true");
    hibernateProperties.put("hibernate.format_sql", "true");
    hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
    hibernateProperties.put("hibernate.default_schema", "gestaopessoas");
    sessionFactory.setHibernateProperties( hibernateProperties );
    return sessionFactory;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory s) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(s);
    return txManager;
  }

}
