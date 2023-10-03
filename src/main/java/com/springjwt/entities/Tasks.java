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

public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  Integer id;
    private  String title;
    private  String description;
    private  double  workhours;
    @Temporal(TemporalType.TIMESTAMP)
    private Date  thedate;
    @PrePersist
    public void prePersist() {
        thedate = new Date(); // Cette ligne sera exécutée avant chaque insertion dans la base de données
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="tasks")
    private Set<TaskTime> taskTimes;

    @JsonIgnore
    @ManyToOne
    Project projects;


}
