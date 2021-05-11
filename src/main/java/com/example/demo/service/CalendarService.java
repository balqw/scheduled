package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {

    public LocalDate getCalendarDate(Integer year, Integer month){
        if(month>12){
            return LocalDate.of(year+1,1,1);
        }else if (month<1){
            return LocalDate.of(year-1,12,1);
        }
         return LocalDate.of(year,month,1);
    }


    public List<Integer> getDaysOfMonth(LocalDate localDate){
        List<Integer>days = new ArrayList<>();
        String srt  = String.valueOf(localDate.getDayOfWeek());
        int emptyDays = 0;

        switch (srt){
            case ("MONDAY"):
                emptyDays = 1;
                break;
            case ("TUESDAY"):
                emptyDays = 2;
                break;
            case ("WEDNESDAY"):
                emptyDays = 3;
                break;
            case ("THURSDAY"):
                emptyDays = 4;
                break;
            case ("FRIDAY"):
                emptyDays = 5;
                break;
            case ("SATURDAY"):
                emptyDays = 6;
                break;
            case ("SUNDAY"):
                emptyDays = 0;
                break;
        }

        int daysOfMonth = localDate.lengthOfMonth();

        for(int i = 0; i < emptyDays; i++){
            days.add(null);
        }

        for(int i=1; i < (daysOfMonth+1) ;i++){
            days.add(i);
        }

        for (int i = 0; i < 45; i++){
            days.add(null);
        }

        return days;
    }

}
