package com.example.demo.configuration;

import com.example.demo.batch.OtherSideWriter;
import com.example.demo.batch.UserProcessor;
import com.example.demo.batch.UserReader;
import com.example.demo.repositories.normal.User;
import com.example.demo.repositories.otherSide.UserFromOtherSide;
import org.hibernate.Session;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory factory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private UserReader userReader;
    @Autowired
    private UserProcessor userProcessor;
    @Autowired
    private OtherSideWriter otherSideWriter;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
   // @Autowired
   // private TaskExecutor taskExecutor;
    //@Autowired
    //private HibernateCursorItemReader hibernateCursorItemReader;
    //@Autowired
   // private EntityManager entityManager;


    @Bean
    public Job createJob() {
        return factory.get("MyJobGeoffrey")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("myStep4").
            <User,UserFromOtherSide> chunk(1).
                reader(userReader)
                .processor(userProcessor)
                .writer(otherSideWriter)
                .faultTolerant()
                .skipLimit(10)
                .skip(NullPointerException.class)
                .transactionManager(platformTransactionManager)
                .build();
    }

    /*
    @Bean
    public HibernateCursorItemReader itemReader(Session session) {
        return new HibernateCursorItemReaderBuilder<User>()
                .name("creditReader")
                .sessionFactory(session.getSessionFactory())
                .queryString("from Users")
                .build();
    }

    @Bean
    public Session session(){
        return  entityManager.unwrap(Session.class);

    }

     */
}
