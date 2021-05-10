package com.example.demo.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Day {
    Integer year;
    String month;
    Integer monthValue;
    Integer day;
    LocalDate localDate;
    LocalTime localTime;
}
