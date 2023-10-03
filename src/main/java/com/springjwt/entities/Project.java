package com.springjwt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Project   implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;

    private Date createdDate;
@JsonIgnore

    @ManyToMany(mappedBy="projectset", cascade = CascadeType.DETACH)
    private Set<User> users;
    @JsonIgnore
    @OneToMany(mappedBy = "projects", cascade = CascadeType.DETACH)
    private Set<Tasks> tasks;

}
