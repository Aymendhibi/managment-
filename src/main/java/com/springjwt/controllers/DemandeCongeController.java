package com.springjwt.controllers;


import com.springjwt.entities.Conge;
import com.springjwt.services.DemandeCongeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;

import java.util.List;

@Tag(name = "demand")
@RestController
@RequestMapping("/project")
public class DemandeCongeController {


    private final DemandeCongeService demandeCongeService;

    @Autowired
    public DemandeCongeController(DemandeCongeService demandeCongeService) {
        this.demandeCongeService = demandeCongeService;
    }

    @PostMapping("/soumettre")
    public ResponseEntity<Conge> soumettreDemandeConge(@RequestBody Conge demandeConge) {
        Conge nouvelleDemande = demandeCongeService.soumettreDemandeConge(demandeConge);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleDemande);
    }

    @PutMapping("/traiter/{id}")
    public ResponseEntity<Conge> traiterDemandeConge(
            @PathVariable Long id,
            @RequestParam boolean acceptee,
            @RequestParam String raisonDecision,
            @RequestParam String gestionnaireNom) {
       Conge demandeTraitee = demandeCongeService.traiterDemandeConge(id, acceptee, raisonDecision, gestionnaireNom);
        return ResponseEntity.ok(demandeTraitee);
    }

    @GetMapping("/liste")
    public List<Conge> getAllDemandesConge() {
        return demandeCongeService.getAllDemandesConge();
    }

    @GetMapping("/consulter/{id}")
    public ResponseEntity<Conge> getDemandeConge(@PathVariable Long id) {
        Conge demande = demandeCongeService.getDemandeCongeById(id);
        if (demande != null) {
            return ResponseEntity.ok(demande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
