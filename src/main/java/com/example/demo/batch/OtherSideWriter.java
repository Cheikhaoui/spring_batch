package com.example.demo.batch;

import com.example.demo.repositories.otherSide.UserFromOtherSide;
import com.example.demo.repositories.otherSide.UserFromOtherSideRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtherSideWriter implements ItemWriter<UserFromOtherSide> {

    @Autowired
    private UserFromOtherSideRepository userFromOtherSideRepository;

    @Override
    public void write(List<? extends UserFromOtherSide> list) throws Exception {
        System.out.println(list);
        UserFromOtherSide userFromOtherSide = userFromOtherSideRepository.save(list.get(0));
        System.out.println(userFromOtherSide);
    }
}
