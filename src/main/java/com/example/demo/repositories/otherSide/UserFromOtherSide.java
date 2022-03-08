package com.example.demo.repositories.otherSide;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@SequenceGenerator(name = "id_generator",sequenceName = "seq_gen2",initialValue = 2)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFromOtherSide implements Serializable {
    @Id
    @GeneratedValue(generator = "seq_gen2")
    private Long id;

    private String firstName2;

    private String lastName3;

    private String email;

    @Temporal(value = TemporalType.DATE)
    private Date birthDate;
}
