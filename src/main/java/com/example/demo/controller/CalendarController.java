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
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    private final TaskService taskService;

    @Autowired
    public CalendarController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String getDay(@RequestParam("y")int years,@RequestParam("m")int month, Model model){
          LocalDate localDate = LocalDate.of(years,month,1);
          List<Integer>days = taskService.getDaysOfMonth(localDate);
          model.addAttribute("date",localDate.getMonth().toString() +  "  " +localDate.getYear());
          model.addAttribute("month",localDate.getMonthValue());
          model.addAttribute("years",localDate.getYear());
          model.addAttribute("days",days);
          return "calendar/calendar";

    }

}
