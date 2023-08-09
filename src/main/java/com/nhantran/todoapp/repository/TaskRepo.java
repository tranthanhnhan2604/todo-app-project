package com.nhantran.todoapp.repository;

import com.nhantran.todoapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    List<Task> findTasksByUserId(Integer userId);
}
