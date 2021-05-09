package com.example.demo.controller;

import com.example.demo.domain.dto.Day;
import com.example.demo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/day")
public class DayController {


    @GetMapping
    public String day(@RequestParam("y") int year, @RequestParam("m") int month, @RequestParam("d") int day,
                        Model model){
        Day dayDto = new Day();
        LocalDate ld = LocalDate.of(year,month,day);
        dayDto.setYear(ld.getYear());
        dayDto.setMonth(ld.getMonth().toString());
        dayDto.setDay(ld.getDayOfMonth());
        model.addAttribute("dayDto",dayDto);
        return "day/index";
    }



}
