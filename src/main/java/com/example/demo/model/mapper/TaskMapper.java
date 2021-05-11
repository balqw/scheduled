package com.example.demo.model.mapper;

import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskDto toTaskDto(Task task);

    Task toTaskEntity(TaskDto taskDto);


}
