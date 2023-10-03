package com.springjwt.services;

import com.springjwt.entities.Project;
import com.springjwt.entities.Team;
import com.springjwt.entities.User;
import com.springjwt.repositories.ProjectRepository;
import com.springjwt.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
@Slf4j
@Service
public class UserService implements  IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
    ProjectRepository projectRepository;
    @Override
    public Long CountTeam(Team team) {
        return userRepository.findteam(team);
    }

    @Override
    public List<User>  affichage() {
       List<User> user =userRepository.findAll();

        return user;
    }

    @Override
    public User getMyProfile(UserDetails userDetails) {
        String cuurentname = userDetails.getUsername();
        User user = userRepository.findUserByUsername(cuurentname);
        return user;
    }


    @Override
    public void affecterprojetAuuser(Long idProject, Long idUser) {
        Project e =projectRepository.findById(idProject).orElse(null);
        User c=userRepository.findById(idUser).orElse(null);
        log.info("Project: "+e.getTitle());
        log.info("User: "+c.getUsername());
        c.getProjectset().add(e);
        userRepository.save(c);


    }


    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
