package com.example.demo.controller;

import com.example.demo.domain.entity.Month;
import com.example.demo.domain.entity.Task;
import com.example.demo.service.TaskService;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String getNewTask(@RequestParam("y")int years,@RequestParam("m")int month, Model model){

          LocalDate localDate = LocalDate.of(years,month,1);
          List<Integer>days = taskService.getDaysOfMonth(localDate);
          model.addAttribute("date",localDate.getMonth().toString() +  "  " +localDate.getYear());
          model.addAttribute("month",localDate.getMonthValue());
          model.addAttribute("years",localDate.getYear());
          model.addAttribute("days",days);

            return "calendar/calendar";

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
