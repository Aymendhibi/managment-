package com.springjwt.services;


import com.springjwt.entities.Reclamation;

public interface IReclamationService {

    Reclamation ajoutreclamationtouser(Reclamation r, String username);

    public  Long countReclamation();
}
