package com.example.demo.controller;

import com.example.demo.domain.dto.Day;
import com.example.demo.domain.dto.TaskDto;
import com.example.demo.domain.entity.Task;
import com.example.demo.domain.mapper.TaskMapper;
import com.example.demo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


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
        dayDto.setMonthValue(ld.getMonthValue());
        dayDto.setDay(ld.getDayOfMonth());
        TaskDto taskDto = new TaskDto();
        taskDto.setDate(ld);
        model.addAttribute("dayDto",dayDto);
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("tasks",tasks);

        return "day/index";
    }

    @PostMapping
    public ModelAndView addTask(@ModelAttribute("taskDto") TaskDto taskDto, ModelMap model){
        //taskDto.setDate(LocalDate.now());
        taskService.createTask(taskDto);
        model.addAttribute("y",taskDto.getDate().getYear());
        model.addAttribute("m",taskDto.getDate().getMonthValue());
        model.addAttribute("d",taskDto.getDate().getDayOfMonth());
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
