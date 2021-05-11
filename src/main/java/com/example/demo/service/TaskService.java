package com.example.demo.service;

import com.example.demo.domain.dto.TaskDto;
import com.example.demo.domain.entity.Task;
import com.example.demo.domain.mapper.TaskMapper;
import com.example.demo.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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


    @Transactional
    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

    @Transactional
    public List<Task> getTasksBetweenDate(LocalDateTime start, LocalDateTime end){
        return taskRepo.getTasksByTimeIsBetween(start,end);
    }

    @Transactional
    public Task findTaskById(Long id){
        return taskRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Boolean existTask(LocalDateTime start, LocalDateTime end){
        return taskRepo.existsTaskByTimeBetween(start,end);
    }

}
