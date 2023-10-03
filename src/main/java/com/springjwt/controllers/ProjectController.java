package com.springjwt.controllers;

import com.springjwt.dto.MessageResponse;
import com.springjwt.entities.*;
import com.springjwt.repositories.UserRepository;
import com.springjwt.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Tag(name = "man")
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    IProjectService iProjectService;
@Autowired
ITasksService iTasksService;

@Autowired
ITaskTimeService iTaskTimeService;
@Autowired
    IUserService iUserService;
@Autowired
TasksService tasksService;
@Autowired
IReclamationService iReclamationService;
@Autowired
ICongeService iCongeService;
@Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Operation(description = "Ceci récupère la liste de tous les Projets")

    @PostMapping("/add")
    public Project addproject(@RequestBody Project p) {
        Project projetp = iProjectService.addproject(p);
        return projetp;
    }
   /* @PostMapping("/add")
    public ProjectDTO addprojectt(@RequestBody ProjectDTO Pp) {
        ProjectDTO projetp = iProjectService.addprojectt(Pp);
        return projetp;
    }*/


    @PutMapping("/modifyProjet")
    public Project modifyProjet(@RequestBody Project p) {
        Project projetp = iProjectService.modifyProjet(p);
        return projetp;
    }

    @DeleteMapping("/deletProjet/{projet-id}")
    public void deletProjet(@PathVariable ("projet-id") Long projectId ) {
         iProjectService.removeProjet(projectId);

    }





    @GetMapping("/allProjet")
    public List<Project> allProjet() {
       List<Project>  projetall = iProjectService.retrieveAllProjet();
        return projetall;
    }

    @PostMapping("/Tasks/create/{projet-id}")
    public Tasks ajoutertasksetproject(@RequestBody Tasks cc, @PathVariable("projet-id") Long projectId) {
        return iTasksService.ajoutertasksetproject(cc, projectId);
    }







    @PutMapping("/Tasks")
    public Tasks modifytask(@RequestBody Tasks task) {
        Tasks taskk = iTasksService.modifytask(task);
        return taskk;
    }

    @DeleteMapping("/Tasks/{task-id}")
    public void deletProjet(@PathVariable ("task-id") Integer taskId ) {
        iTasksService.removeTask(taskId);

    }





    @GetMapping("/Tasks/TaskUser")
    public List<Tasks> alltask() {
        List<Tasks>  taskall = iTasksService.retrieveAlltasks();
        return taskall;
    }


  @GetMapping("/Tasks/tasks-with-time/{weekNumber}")
   public Tasks taskweek(@PathVariable ("weekNumber") Long weeknumber ) {

    Tasks tasks =  iTasksService.retrieveAlltasksparwek(weeknumber);


        return tasks ;
    }

    @GetMapping("/Tasks/export/{weekNumber}")
    public Tasks exportweek(@PathVariable ("weekNumber") Long weeknumber ) {


        Tasks tasks =  iTasksService.ExportAlltasksparwek(weeknumber);


        return tasks ;
    }

    @GetMapping("/Tasks/export/monthly/{createdDate}")
    public ResponseEntity<List<Project>> dateprojet(@PathVariable("createdDate") String dateString) {
        dateString = dateString.replace("\"", "/");
        try {
            // Convertir la chaîne de date en objet Date au format "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdDate = dateFormat.parse(dateString);

            // Appeler le service pour rechercher les projets par date
            List<Project> projects = iProjectService.retrieveAllprojetpardate(createdDate);

            // Retourner une réponse avec le statut 200 OK si tout se passe bien
            return ResponseEntity.ok(projects);
        } catch (ParseException e) {
            // Gérer les erreurs de conversion de date ici
            // Renvoyer une réponse d'erreur 400 Bad Request avec une liste vide
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }



    @GetMapping("/Tasks/export/monthly/supervisor/{createdDate}")
    public ResponseEntity<List<Project>> dateprojetExport(@PathVariable("createdDate") String dateString) {
        try {
            // Convertir la chaîne de date en objet Date au format "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date createdDate = dateFormat.parse(dateString);

            // Appeler le service pour rechercher les projets par date
            List<Project> projects = iProjectService.ExportAllprojet(createdDate);

            // Retourner une réponse avec le statut 200 OK si tout se passe bien
            return ResponseEntity.ok(projects);
        } catch (ParseException e) {
            // Gérer les erreurs de conversion de date ici
            // Renvoyer une réponse d'erreur 400 Bad Request avec une liste vide
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
    @GetMapping("/sum/projet")
    public Long Sumproject() {
        return   iProjectService.SumProjet();

    }


    @GetMapping("/count/team")
    public Long countUserParTeam(@RequestParam("team") Team team) {
        return   iUserService.CountTeam(team);

    }



    @PostMapping("reclamation/{username}")
    public Reclamation ajouterreclamationtouser(@RequestBody Reclamation r, @PathVariable("username") String username){

        return   iReclamationService.ajoutreclamationtouser(r,username);
    }
    @GetMapping("/count/reclamation")
    public Long countreclamation() {
        return   iReclamationService.countReclamation();

    }
    @GetMapping("/countconge")
    public  Long countcounge(){

        return iCongeService.countConge();
    }


    @GetMapping("/alluser")
    public List<User>afichagealluser(){

        return iUserService.affichage();

    }


    @PutMapping("/affecterprojetAuuser/{idProject}/{idUser}")
    @ResponseBody
    public void affecterprojetAuuser(@PathVariable("idProject")
                                        Long idProject,@PathVariable("idUser") Long idUser) {
        iUserService.affecterprojetAuuser(idProject, idUser);
    }










    @GetMapping("/my-tasks")
    public ResponseEntity<List<Tasks>> getMyTasks(@AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUserEmail = userDetails.getUsername();
        List<Tasks> userTasks = tasksService.getTasksForLoggedInUser(loggedInUserEmail);
        return ResponseEntity.ok(userTasks);
    }



    @PutMapping("/Taskss/{taskId}")
    public ResponseEntity<Object> updateTask(@PathVariable int taskId, @RequestBody Tasks updatedTask, @AuthenticationPrincipal User currentUser) {
        try {
            tasksService.updateTask(taskId, updatedTask, currentUser);
            return ResponseEntity.ok().body(new MessageResponse("Tâche mise à jour avec succès."));

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la mise à jour de la tâche.");
        }
    }














    //////////////////////////////////////authhhhhhhhhhhhhhhhhhhhuserrrr






    // Autres méthodes du contrôleur...



}
