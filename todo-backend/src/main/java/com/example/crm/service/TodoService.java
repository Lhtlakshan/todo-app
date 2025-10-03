package com.example.crm.service;

import com.example.crm.dto.TaskDto;
import com.example.crm.entity.TaskEntity;
import com.example.crm.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    //Add task
    public TaskDto addTask(TaskDto task) {
        //map the task dto to TaskDto to save db
          TaskEntity savedTask = todoRepository.save(modelMapper.map(task, TaskEntity.class));
          return modelMapper.map(savedTask, TaskDto.class);
    }

    public List<TaskDto> getAllInCompletedTasks() {
        return todoRepository.findByIsDoneFalse()
                .stream()
                .map(task -> modelMapper.map(task, TaskDto.class)) //map the TaskEntities to TaskDtos
                .toList();
    }

    public TaskDto taskDone(Long id) {
        //find the relevant task
        TaskEntity task = todoRepository.findById(id).orElseThrow(()->
                new RuntimeException("Task not found with id: " + id
                ));
        task.setIsDone(true); //set the task is done
        TaskEntity updatedTask = todoRepository.save(task);
        return modelMapper.map(updatedTask, TaskDto.class);
    }
}
