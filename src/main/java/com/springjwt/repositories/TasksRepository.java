package com.springjwt.repositories;

import com.springjwt.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TasksRepository extends JpaRepository<Tasks,Integer > {






}
