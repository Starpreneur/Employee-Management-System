package com.project.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/employee_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.project.Entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }


    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("hibernate.show_sql","true");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
