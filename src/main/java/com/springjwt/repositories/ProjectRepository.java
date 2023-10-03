package com.springjwt.repositories;

import com.springjwt.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT p FROM Project p WHERE p.createdDate >= :startDate AND p.createdDate < :endDate")
    List<Project> findByCreatedDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT count (p) FROM Project p  WHERE  p.id=:projectId" )
   Project findBy();



}
