package com.example.demo.controller;

import com.example.demo.domain.entity.Month;
import com.example.demo.domain.entity.Task;
import com.example.demo.service.CalendarService;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping()
    public String getCalendar(@RequestParam("year")int year,@RequestParam("month")int month, Model model){

          LocalDate date = calendarService.getCalendarDate(year,month);
          List<Integer>days = calendarService.getDaysOfMonth(date);
          model.addAttribute("month",date.getMonthValue());
          model.addAttribute("year",date.getYear());
          model.addAttribute("days",days);
          model.addAttribute("calendarDate",date);
          return "calendar/calendar";

    }

}
