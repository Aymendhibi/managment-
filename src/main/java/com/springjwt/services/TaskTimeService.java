package com.springjwt.services;

import com.springjwt.entities.TaskTime;
import com.springjwt.entities.Tasks;
import com.springjwt.repositories.TaskTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskTimeService implements ITaskTimeService{
    @Autowired
    TaskTimeRepository taskTimeRepository;
    @Override
    public TaskTime weekNumber(Tasks t, Long weeknumber  ) {
        return taskTimeRepository.findTaskTimesByTasksAndWeeknumber(t,weeknumber);
    }
}
