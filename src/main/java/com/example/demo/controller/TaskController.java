package com.example.demo.controller;

import com.example.demo.domain.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getNewTask(){
        return "day/index";
    }

    @GetMapping("/new")
    public String getCreatePage(Model model){
        model.addAttribute("task",new Task());
        return "day/new";
    }

    @PostMapping
    public String saveTask(@ModelAttribute("task") Task task){
        taskService.createTask(task);
        return "day/index";
    }

}
