package com.springjwt.repositories;


import com.springjwt.entities.TaskTime;
import com.springjwt.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskTimeRepository extends JpaRepository<TaskTime,Long> {



    TaskTime findTaskTimeByWeeknumber(Long weeknumber);

    TaskTime findTaskTimesByTasksAndWeeknumber(Tasks t, Long weeknumber);


}
