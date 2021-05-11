package com.example.demo.service;

import com.example.demo.model.dto.Days;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final TaskService taskService;


    public LocalDate getCalendarDate(Integer year, Integer month){
        if (month>12) {
            return LocalDate.of(year, 12, 1).plusMonths(1);
        }
        else if (month<1) {
            return LocalDate.of(year, 1, 1).minusMonths(1);
        }
        return LocalDate.of(year,month,1);
    }

    public Days getDaysOfMonth(LocalDate localDate) {
        List<Integer> days = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(localDate, LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(localDate, LocalTime.of(23, 59));
        List<Boolean> event = new ArrayList<>();

        Days daysDto = new Days();

        int emptyDays = localDate.getDayOfWeek().getValue();
        int day = 1;

        for (int i = 0; i < 42; i++) {

            if (emptyDays >1) {
                days.add(null);
                event.add(false);
                emptyDays--;
            } else if (day <= localDate.lengthOfMonth()) {
                days.add(day);
                day++;
                event.add(taskService.existTask(start, end));

                start = start.plusDays(1);
                end = end.plusDays(1);

            } else {
                days.add(null);
                event.add(false);
            }
        }
        daysDto.setDay(days);
        daysDto.setEvent(event);
        return daysDto;
    }


}
