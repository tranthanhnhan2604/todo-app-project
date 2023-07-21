package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TodoDto;
import com.nhantran.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto userDto) {
        return new ResponseEntity<>(todoService.createTodo(userDto), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Integer id) {
        return new ResponseEntity<>(todoService.updateTodo(todoDto,id), HttpStatus.OK);
    }
    @GetMapping("/{todoId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Integer todoId) {
        return  new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }
    @GetMapping("/task/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TodoDto>> getAllTodosByTaskId(@PathVariable Integer id) {
        return new ResponseEntity<>(todoService.getAllTodoByTaskId(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
