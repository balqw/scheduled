package com.example.demo.controller;

import com.example.demo.model.dto.Days;
import com.example.demo.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final CalendarService calendarService;

    @GetMapping()
    public ModelAndView getCalendar(ModelMap model){
        LocalDateTime localDate = LocalDateTime.now();
        model.addAttribute("year",localDate.getYear());
        model.addAttribute("month",localDate.getMonthValue());
        return new ModelAndView("redirect:/calendar", model);
    }
}
