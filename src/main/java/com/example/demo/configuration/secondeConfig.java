package com.example.demo.configuration;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "otherEntityManager",
        transactionManagerRef = "otherTransactionManager",
        basePackages = {"com.example.demo.repositories.otherSide"}
)
@EnableTransactionManagement
public class secondeConfig {


    @Bean(name = "otherEntityManager")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean (EntityManagerFactoryBuilder builder
    , @Qualifier("otherDataSource")DataSource dataSource){
      return   builder.dataSource(dataSource).packages("com.example.demo.repositories.otherSide")
                .persistenceUnit("other").build();
    }

    @Bean(name = "otherProperties")
    @ConfigurationProperties("second.jpa")
    @Primary
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "otherDataSource")
    @Primary
    public DataSource dataSource(@Qualifier("otherProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "otherTransactionManager")
    @Primary
    public JpaTransactionManager transactionManager(@Qualifier("otherEntityManager") EntityManagerFactory domainsEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(domainsEntityManager);
        return transactionManager;
    }



}
