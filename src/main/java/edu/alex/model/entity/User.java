package edu.alex.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", length = 128)
    private String firstName;

    @Column(name = "last_name", length = 128)
    private String lastName;

}
