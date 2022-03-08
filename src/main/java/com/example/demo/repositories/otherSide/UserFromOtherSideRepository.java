package com.example.demo.repositories.otherSide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface UserFromOtherSideRepository extends CrudRepository<UserFromOtherSide,Long> {
}
