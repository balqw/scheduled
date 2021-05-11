package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class Days {
    private List<Integer> day;
    private List<Boolean> event;
}
