package com.springjwt.repositories;

import com.springjwt.entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeCongeRepository extends JpaRepository<Conge,Long> {
}
