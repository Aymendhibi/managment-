package com.springjwt.services;


import com.springjwt.entities.Project;
import com.springjwt.entities.TaskTime;
import com.springjwt.entities.Tasks;
import com.springjwt.entities.User;
import com.springjwt.repositories.ProjectRepository;
import com.springjwt.repositories.TaskTimeRepository;
import com.springjwt.repositories.TasksRepository;
import com.springjwt.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class TasksService implements  ITasksService{

    @Autowired
    TasksRepository tasksRepository ;
    @Autowired
    ProjectRepository projectRepository;
@Autowired
TaskTimeRepository taskTimeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Tasks ajoutertasksetproject(Tasks cc, Long projectId) {


        Project project= projectRepository.findById(projectId).get();
  cc.setProjects(project);
        tasksRepository.save(cc);



        return cc;
    }

    @Override
    public void removeTask(Integer taskId) {
        tasksRepository.deleteById(taskId);
    }

    @Override
    public Tasks modifytask(Tasks task) {
        return  tasksRepository.save(task);
    }

    @Override
    public List<Tasks> retrieveAlltasks() {
        return tasksRepository.findAll();
    }

    @Override
    public Tasks retrieveAlltasksparwek(Long weeknumber) {




        Tasks tasks=  taskTimeRepository.findTaskTimeByWeeknumber(weeknumber).getTasks();
        return tasks;
    }

    @Override
    public Tasks ExportAlltasksparwek(Long weeknumber) {




       Tasks tasks=  taskTimeRepository.findTaskTimeByWeeknumber(weeknumber).getTasks();
        return tasks;
    }


    public List<Tasks> getTasksForLoggedInUser(String loggedInUserEmail) {
        User user = userRepository.findFirstByEmail(loggedInUserEmail);
        Set<Project> userProjects = user.getProjectset();

        List<Tasks> userTasks = new ArrayList<>();
        for (Project project : userProjects) {
            userTasks.addAll(project.getTasks());
        }

        return userTasks;
    }





    public void updateTask(int taskId, Tasks updatedTask, User currentUser) {
        Tasks task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Tâche introuvable"));



        int isoWeekNumber = getIsoWeekNumber(LocalDate.now());

        TaskTime taskTime = task.getTaskTimes().stream()
                .filter(tt -> tt.getWeeknumber() == isoWeekNumber)
                .findFirst()
                .orElse(null);

        if (taskTime == null) {
            taskTime = new TaskTime();
            taskTime.setWeeknumber(isoWeekNumber);
            task.getTaskTimes().add(taskTime);
        }

        double timeSpentThisWeek = taskTime.getTimeSpent();
        double totalTimeSpent = timeSpentThisWeek + updatedTask.getWorkhours();

        task.setWorkhours(totalTimeSpent);
        taskTime.setTimeSpent(totalTimeSpent);

        tasksRepository.save(task);
    }

    private int getIsoWeekNumber(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int isoWeekNumber = date.get(weekFields.weekOfWeekBasedYear());

        // Handle the special case of ISO week 53 in the previous year
        if (isoWeekNumber == 53 && date.getMonthValue() == 1) {
            isoWeekNumber = 1;
        }

        return isoWeekNumber;
    }



}
