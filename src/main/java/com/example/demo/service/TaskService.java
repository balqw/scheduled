package com.example.demo.service;

import com.example.demo.domain.dto.Day;
import com.example.demo.domain.dto.TaskDto;
import com.example.demo.domain.entity.Task;
import com.example.demo.domain.mapper.TaskMapper;
import com.example.demo.repo.TaskRepo;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;



    @Transactional
    public TaskDto createTask(TaskDto taskDto){
        Task task = taskMapper.toTaskEntity(taskDto);
        task.setTime(LocalDateTime.of(taskDto.getDate(),taskDto.getTaskTime()));
        taskRepo.save(task);
        return taskDto;
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

    @Transactional
    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

    @Transactional
    public List<Task> getTasksBetweenDate(LocalDateTime start, LocalDateTime end){
        return taskRepo.getTasksByTimeIsBetween(start,end);
    }

    public Task findTaskById(Long id){
        return taskRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }


}
