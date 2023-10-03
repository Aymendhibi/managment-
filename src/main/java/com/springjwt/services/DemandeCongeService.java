package com.springjwt.services;

import com.springjwt.entities.Conge;
import com.springjwt.repositories.DemandeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
@Service
public class DemandeCongeService {



    private final DemandeCongeRepository demandeCongeRepository;

    @Autowired
    public DemandeCongeService(DemandeCongeRepository demandeCongeRepository) {
        this.demandeCongeRepository = demandeCongeRepository;
    }

    public Conge soumettreDemandeConge(Conge demandeConge) {
        if (!isValidDateRange(demandeConge)) {
            throw new IllegalArgumentException("La date de fin doit être supérieure à la date de début.");
        }

        demandeConge.setStatut("En attente");
        return demandeCongeRepository.save(demandeConge);
    }

    public Conge traiterDemandeConge(Long id, boolean acceptee, String raisonDecision, String gestionnaireNom) {
        Conge demande = demandeCongeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Demande de congé non trouvée avec l'ID : " + id));

        demande.setStatut(acceptee ? "Acceptée" : "Refusée");
        demande.setRaisonDecision(raisonDecision);
        demande.setGestionnaireNom(gestionnaireNom);

        return demandeCongeRepository.save(demande);
    }

    private boolean isValidDateRange(Conge demandeConge) {
        return demandeConge.getDateDebut().before(demandeConge.getDateFin());
    }
    public List<Conge> getAllDemandesConge() {
        return demandeCongeRepository.findAll();
    }

    public Conge getDemandeCongeById(Long id) {
        return demandeCongeRepository.findById(id).orElse(null);
    }
}
