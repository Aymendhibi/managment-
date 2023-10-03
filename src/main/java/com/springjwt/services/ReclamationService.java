package com.springjwt.services;

import com.springjwt.entities.Reclamation;
import com.springjwt.entities.User;
import com.springjwt.repositories.ReclamationRepository;
import com.springjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReclamationService implements IReclamationService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReclamationRepository reclamationRepository;
    @Override


    public Reclamation ajoutreclamationtouser(Reclamation r, String username) {
        User user= userRepository.findUserByUsername(username);
        r.setUsers(user);
        reclamationRepository.save(r);



        return r;
    }

    @Override
    public Long countReclamation() {
        return reclamationRepository.count();
    }


}

