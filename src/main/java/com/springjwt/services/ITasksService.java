package com.springjwt.services;




import com.springjwt.entities.Tasks;

import java.util.List;

public interface ITasksService {
    Tasks ajoutertasksetproject(Tasks cc, Long projectId);
    public void removeTask(Integer taskId);
    public Tasks modifytask(Tasks task);
    public List<Tasks> retrieveAlltasks();
    public Tasks retrieveAlltasksparwek(Long weeknumber);
    public Tasks ExportAlltasksparwek(Long weeknumber);
}
