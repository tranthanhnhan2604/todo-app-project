package com.nhantran.todoapp.service;

import com.nhantran.todoapp.dto.TodoDto;

import java.util.List;


public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);

    TodoDto updateTodo(TodoDto todoDto, Integer id);

    TodoDto getTodoById(Integer id);

    List<TodoDto> getAllTodoByTaskId(Integer taskId);

    void deleteTodoById(Integer id);
}
