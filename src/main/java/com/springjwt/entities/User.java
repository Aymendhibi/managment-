package com.springjwt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;


@Entity
@Table(name = "users")
@Data

public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;


    private String email;

    private String password;


    @Enumerated
    role role;

    @Enumerated
    Team team;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private     Set<Project> projectset;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="users")
    Set <Reclamation> reclamations;

}
