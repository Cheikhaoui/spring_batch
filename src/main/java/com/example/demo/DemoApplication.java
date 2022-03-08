package com.example.demo;

import com.example.demo.repositories.normal.User;
import com.example.demo.repositories.otherSide.UserFromOtherSide;
import com.example.demo.repositories.normal.UserRepository;
import com.example.demo.repositories.otherSide.UserFromOtherSideRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.persistence.Entity;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
       // User user = User.builder().firstName("hicham").lastName("cheikhaoui").build();
       // userRepository.save(user);
       // jobLauncher.run(job,new JobParameters());
        /*
        //4.2. Query Methods
        System.out.println("hello");
        User user = User.builder().firstName("hicham").lastName("cheikhaoui").build();
        UserFromOtherSide userFromOtherSide = UserFromOtherSide.builder().firstName2("hichamk").lastName3("cheikhaouik").build();
        userFromOtherSideRepository.save(userFromOtherSide);
        userRepository.save(user);
        System.out.println(userRepository.countByLastName("cheikhaoui"));
        System.out.println("end");
         */
    }
}
