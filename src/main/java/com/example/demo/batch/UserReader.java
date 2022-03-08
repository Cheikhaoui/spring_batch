package com.example.demo.batch;

import com.example.demo.repositories.normal.User;
import com.example.demo.repositories.normal.UserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component
public class UserReader implements ItemReader<User> {

    UserRepository userRepository;

    IteratorItemReader iteratorItemReader;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.iteratorItemReader = new IteratorItemReader<>(userRepository.findAll());
    }

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(iteratorItemReader == null){
            return null;
        }
        try {
            User user = (User) iteratorItemReader.read();
            if (user == null) {
                return null;
            } else {
                return user;
            }
        }catch (Exception e){
            return null;
        }
    }
}
