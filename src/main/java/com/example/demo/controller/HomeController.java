package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public ModelAndView getCalendar(ModelMap model){
        LocalDateTime localDate = LocalDateTime.now();
        model.addAttribute("year",localDate.getYear());
        model.addAttribute("month",localDate.getMonthValue());
        return new ModelAndView("redirect:/calendar", model);
    }
}
