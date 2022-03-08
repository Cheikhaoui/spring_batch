package com.example.demo.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstEntityManager",
        transactionManagerRef = "firstTransactionManager",
        basePackages = {"com.example.demo.repositories.normal"}
)
@EnableTransactionManagement
public class firstConfig {


    @Bean(name = "firstEntityManager")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean (EntityManagerFactoryBuilder builder
    , @Qualifier("firstDataSource")DataSource dataSource){
      return   builder.dataSource(dataSource).packages("com.example.demo.repositories.normal")
                .persistenceUnit("first").build();
    }

    @Bean(name = "firstProperties")
    @ConfigurationProperties("spring.jpa")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "firstDataSource")
    public DataSource dataSource(@Qualifier("firstProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "firstTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("firstEntityManager") EntityManagerFactory domainsEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(domainsEntityManager);
        return transactionManager;
    }




}
