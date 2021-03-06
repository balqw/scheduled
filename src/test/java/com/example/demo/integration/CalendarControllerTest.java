package com.example.demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalendarControllerTest {

    @Autowired
    private MockMvc mvc;

    LocalDate date = LocalDate.now();
    String year = String.valueOf(date.getYear());
    String month = String.valueOf(date.getMonthValue());

    @Test
    void getCalendar() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/calendar")
                .param("year",year)
                .param("month",month))
                .andExpect(status().isOk());
    }
}