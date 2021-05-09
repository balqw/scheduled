package com.example.demo.controller;

import com.example.demo.domain.dto.Day;
import com.example.demo.domain.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/day")
public class DayController {

    private final TaskService taskService;

    public DayController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String day(@RequestParam("y") int year, @RequestParam("m") int month, @RequestParam("d") int day,
                      Model model){
        Day dayDto = new Day();
        LocalDate ld = LocalDate.of(year,month,day);
        LocalDateTime start = LocalDateTime.of(ld,LocalTime.of(00,00));
        LocalDateTime end = LocalDateTime.of(ld,LocalTime.of(23,59));
        List<Task>tasks = taskService.getTasksBetweenDate(start,end);
        dayDto.setYear(ld.getYear());
        dayDto.setMonth(ld.getMonth().toString());
        dayDto.setDay(ld.getDayOfMonth());
        model.addAttribute("dayDto",dayDto);
        model.addAttribute("task", new Task());
        model.addAttribute("tasks",tasks);

        return "day/index";
    }

    @PostMapping
    public ModelAndView addTask(@ModelAttribute("task") Task task, ModelMap model){
        taskService.createTask(task);
        model.addAttribute("y",task.getTime().getYear());
        model.addAttribute("m",task.getTime().getMonthValue());
        model.addAttribute("d",task.getTime().getDayOfMonth());
        return new ModelAndView("redirect:/day", model);
    }

    @GetMapping("{id}/delete")
    public ModelAndView deleteTask(@PathVariable("id") Long id, ModelMap model){
        Task task = taskService.findTaskById(id);
        taskService.deleteTask(id);
        model.addAttribute("y",task.getTime().getYear());
        model.addAttribute("m",task.getTime().getMonthValue());
        model.addAttribute("d",task.getTime().getDayOfMonth());
        return new ModelAndView("redirect:/day", model);
    }



}
