package com.example.demo.repositories.normal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@SequenceGenerator(name = "id_generator",sequenceName = "seq_gen",initialValue = 0)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "seq_gen")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Temporal(value = TemporalType.DATE)
    private Date birthDate;


}
