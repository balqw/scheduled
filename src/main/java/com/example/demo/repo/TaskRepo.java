package com.example.demo.repo;

import com.example.demo.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository < Task, Long> {
}
