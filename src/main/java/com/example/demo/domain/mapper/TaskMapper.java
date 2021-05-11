package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.TaskDto;
import com.example.demo.domain.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskDto toTaskDto(Task task);

    Task toTaskEntity(TaskDto taskDto);


}
