package com.example.demo.service;

import com.example.demo.domain.entity.Task;
import com.example.demo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task){
        taskRepo.save(task);
        return task;
    }

    public Task findByDate(){
        return null;
    }

    public void deleteTask(){

    }




}
