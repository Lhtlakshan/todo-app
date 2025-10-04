package com.example.crm.service;

import com.example.crm.dto.TaskDto;
import com.example.crm.entity.TaskEntity;
import com.example.crm.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TodoService todoService;

    private TaskEntity taskEntity;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {

        //mock entity and dto
        taskEntity = new TaskEntity(1L, "Test Task", "This is a test task", false);
        taskDto = new TaskDto(1L, "Test Task", "This is a test task", false);
    }

    @Test
    void testAddTask() {
        when(modelMapper.map(taskDto, TaskEntity.class)).thenReturn(taskEntity);
        when(todoRepository.save(taskEntity)).thenReturn(taskEntity);
        when(modelMapper.map(taskEntity, TaskDto.class)).thenReturn(taskDto);

        TaskDto result = todoService.addTask(taskDto);

        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(todoRepository, times(1)).save(taskEntity);
    }

    @Test
    void testGetAllInCompletedTasks() {
        when(todoRepository.findByIsDoneFalse()).thenReturn(Arrays.asList(taskEntity));
        when(modelMapper.map(taskEntity, TaskDto.class)).thenReturn(taskDto);

        List<TaskDto> tasks = todoService.getAllInCompletedTasks();

        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }

    @Test
    void testTaskDone() {
        //this task is not done
        when(todoRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        taskEntity.setIsDone(true); //set done
        when(todoRepository.save(taskEntity)).thenReturn(taskEntity);

        TaskDto doneDto = new TaskDto(1L, "Test Task", "This is a test task", true);
        when(modelMapper.map(taskEntity, TaskDto.class)).thenReturn(doneDto);

        TaskDto result = todoService.taskDone(1L);

        assertEquals(true, result.getIsDone());
        verify(todoRepository, times(1)).save(taskEntity);
    }
}
