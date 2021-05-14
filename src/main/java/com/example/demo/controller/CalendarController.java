package com.example.demo.controller;


import ch.qos.logback.core.hook.DelayingShutdownHook;
import com.example.demo.model.Step;
import com.example.demo.model.dto.Days;
import com.example.demo.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
@Validated
public class CalendarController {

    private final CalendarService calendarService;


    @GetMapping()
    public String getCalendar(@RequestParam(value = "step",required = false) Optional<String> step, @RequestParam("year") Integer year, @RequestParam("month") @Min(0) @Max(13) Integer month, Model model) {
        LocalDate date;
        if(step.isEmpty()) {
             date = LocalDate.of(year, month, 1);
        }else
             date = calendarService.getCalendarDate(year, month, Step.valueOf(step.get()));

        Days days = calendarService.getDaysOfMonth(date);
        model.addAttribute("month", date.getMonthValue());
        model.addAttribute("year", date.getYear());
        model.addAttribute("days", days);
        model.addAttribute("calendarDate", date);

        return "calendar/calendar";

    }


}
