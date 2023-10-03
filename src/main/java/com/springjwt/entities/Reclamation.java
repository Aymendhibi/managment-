package com.springjwt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private  String mailto;

    private  String mailcc;
    private   String  subject;
    private  String   body;
    @JsonIgnore
    @ManyToOne
    User users;






}
