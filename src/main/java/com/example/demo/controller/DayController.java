package com.example.demo.controller;

import com.example.demo.model.dto.DayInfo;
import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;
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
    public String day(@RequestParam("year") Integer year, @RequestParam("month") Integer month, @RequestParam("day") Integer day,
                      Model model){
        DayInfo dayInfo = new DayInfo();
        LocalDate ld = LocalDate.of(year,month,day);
        LocalDateTime start = LocalDateTime.of(ld,LocalTime.of(00,00));
        LocalDateTime end = LocalDateTime.of(ld,LocalTime.of(23,59));
        List<Task>tasks = taskService.getTasksBetweenDate(start,end);
        dayInfo.setYear(ld.getYear());
        dayInfo.setMonth(ld.getMonth().toString());
        dayInfo.setMonthValue(ld.getMonthValue());
        dayInfo.setDay(ld.getDayOfMonth());
        TaskDto taskDto = new TaskDto();
        taskDto.setDate(ld);
        model.addAttribute("dayInfo", dayInfo);
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("tasks",tasks);
        return "day/index";
    }

    @PostMapping
    public ModelAndView addTask(@ModelAttribute("taskDto") TaskDto taskDto, ModelMap model){

        taskService.createTask(taskDto);
        model.addAttribute("year",taskDto.getDate().getYear());
        model.addAttribute("month",taskDto.getDate().getMonthValue());
        model.addAttribute("day",taskDto.getDate().getDayOfMonth());
        return new ModelAndView("redirect:/day", model);
    }

    @GetMapping("{id}/delete")
    public ModelAndView deleteTask(@PathVariable("id") Long id, ModelMap model){
        Task task = taskService.findTaskById(id);
        taskService.deleteTask(id);
        model.addAttribute("year",task.getTime().getYear());
        model.addAttribute("month",task.getTime().getMonthValue());
        model.addAttribute("day",task.getTime().getDayOfMonth());
        return new ModelAndView("redirect:/day", model);
    }



}
