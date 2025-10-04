package com.example.crm.config;

import com.example.crm.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public TodoService todoService() {
        return mock(TodoService.class);
    }

    @Bean
    public ModelMapper testModelMapper() {
        return mock(ModelMapper.class);
    }
}
