package com.springjwt.repositories;

import com.springjwt.entities.Team;
import com.springjwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
    @Query("Select count(p) from User p WHERE p.team=:team")
    Long findteam(@Param("team") Team team);

    User findUserByUsername(String username);
    //User findByUsername(String username);






}
