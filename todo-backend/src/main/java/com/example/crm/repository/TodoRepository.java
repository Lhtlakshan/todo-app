package com.example.crm.repository;

import com.example.crm.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByIsDoneFalse();
}
