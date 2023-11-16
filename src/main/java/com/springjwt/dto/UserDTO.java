package com.springjwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springjwt.entities.Project;
import com.springjwt.entities.Reclamation;
import com.springjwt.entities.Team;
import com.springjwt.entities.role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String email;
    @Enumerated
    com.springjwt.entities.role role;




    private String username;



    private String password;



    @Enumerated
    Team team;





}
