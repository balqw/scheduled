package com.example.demo.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TaskDto {
    public Long id;
    public String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime taskTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime localDateTime;
}
