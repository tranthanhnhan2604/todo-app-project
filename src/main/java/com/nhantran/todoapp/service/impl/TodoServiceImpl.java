package com.nhantran.todoapp.service.impl;

import com.nhantran.todoapp.dto.TodoDto;
import com.nhantran.todoapp.entity.Todo;
import com.nhantran.todoapp.exception.TodoNotFoundException;
import com.nhantran.todoapp.repository.TodoRepo;
import com.nhantran.todoapp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepo todoRepo;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        return TodoDto.convertToTodoDto(
                todoRepo.save(
                        TodoDto.convertToTodo(todoDto)
                )
        );
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Integer id) {
        Todo todo= todoRepo.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo with id = " + id + " not be found"));
        todo.setTitle(todoDto.getTitle());
        todo.setModifiedTime(ZonedDateTime.now());
        return TodoDto.convertToTodoDto(todoRepo.save(todo));
    }

    @Override
    public TodoDto getTodoById(Integer id) {
        if (id == null) {
            log.error("Todo id is null");
            return null;
        }
        return todoRepo.findById(id)
                .map(TodoDto::convertToTodoDto)
                .orElseThrow(() -> new TodoNotFoundException("Todo with id = " + id + " not be found"));
    }

    @Override
    public List<TodoDto> getAllTodoByTaskId(Integer taskId) {
        return todoRepo.findTodoByTaskId(taskId)
                .stream()
                .map(TodoDto::convertToTodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTodoById(Integer id) {
        if (id == null){
            log.error("Todo id is null");
            return;
        }
        todoRepo.deleteById(id);
    }
}
