package com.springjwt.services;

import com.springjwt.entities.Project;
import com.springjwt.entities.Tasks;
import com.springjwt.entities.User;
import com.springjwt.repositories.ProjectRepository;
import com.springjwt.repositories.TasksRepository;
import com.springjwt.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectService implements IProjectService{

  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  private TasksRepository tasksRepository;


  public ProjectService(ProjectRepository projectRepository) {
  }


  @Override
    public Project addproject(Project P) {
        return projectRepository.save(P);
    }
  /*
  @Override
  public ProjectDTO addprojectt(ProjectDTO P) {
    return null;
  }



  @Override
  public ProjectDTO addprojectt(ProjectDTO Pp) {
    List<String> errors = ProjectValdidator.validate(Pp);
    if (!errors.isEmpty()) {
      log.error("Project is not valid", Pp);
      // Handle validation errors as needed
    }
   // Project savedProject = projectdtoREp.save(ProjectDTO.toEntity(Pp));
    return ProjectDTO.fromEntity(savedProject);
  }
*/

  @Override
  public Project removeProjet(Long projectId) {
    projectRepository.deleteById(projectId);

    return null;
  }

  @Override
  public Project modifyProjet(Project P) {
     return projectRepository.save(P);
  }

  @Override
  public List<Project> retrieveAllProjet() {
    List<Project> projectall= projectRepository.findAll();
    return projectall;
  }







  @Override
  public List<Project> retrieveAllprojetpardate(Date createdDate) {
    // Créer une date de début (00:00:00) pour la journée spécifiée
    Calendar calendarStart = Calendar.getInstance();
    calendarStart.setTime(createdDate);
    calendarStart.set(Calendar.HOUR_OF_DAY, 0);
    calendarStart.set(Calendar.MINUTE, 0);
    calendarStart.set(Calendar.SECOND, 0);

    // Créer une date de fin (23:59:59) pour la même journée
    Calendar calendarEnd = Calendar.getInstance();
    calendarEnd.setTime(createdDate);
    calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
    calendarEnd.set(Calendar.MINUTE, 59);
    calendarEnd.set(Calendar.SECOND, 59);

    Date startDate = calendarStart.getTime();
    Date endDate = calendarEnd.getTime();

    List<Project> tasks = projectRepository.findByCreatedDate(startDate, endDate);
    return tasks;
  }


  @Override
  public List<Project> ExportAllprojet(Date createdDate) {




    Calendar calendarStart = Calendar.getInstance();
    calendarStart.setTime(createdDate);
    calendarStart.set(Calendar.HOUR_OF_DAY, 0);
    calendarStart.set(Calendar.MINUTE, 0);
    calendarStart.set(Calendar.SECOND, 0);


    Calendar calendarEnd = Calendar.getInstance();
    calendarEnd.setTime(createdDate);
    calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
    calendarEnd.set(Calendar.MINUTE, 59);
    calendarEnd.set(Calendar.SECOND, 59);

    Date startDate = calendarStart.getTime();
    Date endDate = calendarEnd.getTime();

    List<Project> tasks = projectRepository.findByCreatedDate(startDate, endDate);
    return tasks;
  }

  @Override
  public Long SumProjet() {
    return projectRepository.count();
  }

  @Autowired
  private UserRepository userRepository;

  public List<Tasks> getTasksForCurrentUser(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + userId));
    return user.getProjectset().stream()
            .flatMap(project -> project.getTasks().stream())
            .collect(Collectors.toList());
  }
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }
  public List<User> getAlluser() {
    return userRepository.findAll();
  }

}
