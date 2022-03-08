package com.example.demo.repositories.normal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRES_NEW)
public interface UserRepository extends CrudRepository<User,Long> {
    long countByLastName(String lastName);
}
