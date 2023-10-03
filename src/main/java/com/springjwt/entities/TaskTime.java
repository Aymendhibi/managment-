package com.springjwt.entities;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class TaskTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  long id;
   private  long  weeknumber;
   private   Double timeSpent;
   @ManyToOne
    Tasks tasks;



}
