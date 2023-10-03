package com.springjwt.services;


import com.springjwt.entities.TaskTime;
import com.springjwt.entities.Tasks;

public interface ITaskTimeService {
    TaskTime weekNumber(Tasks t, Long weeknumber);
}
