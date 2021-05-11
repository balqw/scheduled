package com.example.demo.repository;

import com.example.demo.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepo extends JpaRepository < Task, Long> {
    @Query(value = "SELECT * FROM task WHERE  time BETWEEN :s AND :e ", nativeQuery = true)
    List<Task> getTasksByTimeIsBetween(LocalDateTime s, LocalDateTime e);


    Boolean existsTaskByTimeBetween(LocalDateTime s,LocalDateTime e);
}
