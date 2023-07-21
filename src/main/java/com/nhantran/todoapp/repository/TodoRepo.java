package com.nhantran.todoapp.repository;

import com.nhantran.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo,Integer> {

    List<Todo> findTodoByTaskId(Integer taskId);
}
