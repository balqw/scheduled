package com.example.demo.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DayInfo {
    private Integer year;
    private String month;
    private Integer monthValue;
    private Integer day;
    private LocalDate localDate;
    private LocalTime localTime;
}
