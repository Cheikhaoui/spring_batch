package com.example.demo.batch;

import com.example.demo.repositories.normal.User;
import com.example.demo.repositories.otherSide.UserFromOtherSide;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor implements ItemProcessor<User, UserFromOtherSide> {


    @Override
    public UserFromOtherSide process(User user) throws Exception {
        System.out.println("traitement user" + user);
        if(user.getFirstName().equals("hicham")){
            throw new NullPointerException();
        }
        return  UserFromOtherSide.builder().firstName2(user.getFirstName())
                .lastName3(user.getLastName()).build();
    }
}
