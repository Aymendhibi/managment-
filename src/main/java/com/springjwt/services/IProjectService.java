package com.springjwt.services;



import com.springjwt.entities.Project;

import java.util.Date;
import java.util.List;

public interface IProjectService {

    public Project addproject(Project P);
    //public ProjectDTO addprojectt(ProjectDTO P);
    public Project removeProjet(Long projectId);
    public Project modifyProjet(Project projet);
    public List<Project> retrieveAllProjet();

    public List<Project> retrieveAllprojetpardate(Date createdDate);
    public List<Project> ExportAllprojet(Date createdDate);
    public Long SumProjet();





}
