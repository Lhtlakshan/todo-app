package com.example.crm.controller;

import com.example.crm.config.TestConfig;
import com.example.crm.dto.TaskDto;
import com.example.crm.service.TodoService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
@Import(TestConfig.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoService todoService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testAddTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Test Task", "This is a test task", false);

        when(todoService.addTask(any(TaskDto.class))).thenReturn(taskDto);

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Test Task\",\"description\":\"This is a test task\",\"isDone\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Task added successfully"))
                .andExpect(jsonPath("$.data.title").value("Test Task"));
    }

    @Test
    void testGetAllInCompletedTasks() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Test Task", "This is a test task", false);
        when(todoService.getAllInCompletedTasks()).thenReturn(Arrays.asList(taskDto));

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("All tasks"))
                .andExpect(jsonPath("$.data[0].title").value("Test Task"));
    }

    @Test
    void testTaskDone() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Test Task", "This is a test task", true);
        when(todoService.taskDone(1L)).thenReturn(taskDto);

        mockMvc.perform(patch("/api/todo/1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Task done"))
                .andExpect(jsonPath("$.data.title").value("Test Task"))
                .andExpect(jsonPath("$.data.isDone").value(true));
    }

}
