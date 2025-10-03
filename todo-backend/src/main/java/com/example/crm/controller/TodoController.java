package com.example.crm.controller;

import com.example.crm.dto.TaskDto;
import com.example.crm.service.TodoService;
import com.example.crm.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todo")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    ResponseEntity<ApiResponse<TaskDto>> addTask(@RequestBody TaskDto task){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("Task added successfully", todoService.addTask(task)
                    ));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>("Task could not be added", null
                    ));
        }
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<TaskDto>>> getAllInCompletedTasks(){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("All tasks", todoService.getAllInCompletedTasks()
                    ));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>("Task could be found", null
                    ));
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse<TaskDto>>taskDone(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("Task done", todoService.taskDone(id)
                    ));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>("Task could not be done", null
                    ));
        }
    }
}
