package com.springjwt.services;



import com.springjwt.entities.Team;
import com.springjwt.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    public  Long CountTeam(Team team);

    public List<User> affichage();
    User getMyProfile(UserDetails userDetails);
    void affecterprojetAuuser(Long idProject, Long idUser);



}
