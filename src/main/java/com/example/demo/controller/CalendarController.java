package com.example.demo.controller;

import com.example.demo.domain.dto.Days;
import com.example.demo.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping()
    public String getCalendar(@RequestParam("year") Integer year, @RequestParam("month") Integer month, Model model){

          LocalDate date = calendarService.getCalendarDate(year,month);
          Days days = calendarService.getDaysOfMonth(date);
          model.addAttribute("month",date.getMonthValue());
          model.addAttribute("year",date.getYear());
          model.addAttribute("days",days);
          model.addAttribute("calendarDate",date);
          return "calendar/calendar";

    }

}
