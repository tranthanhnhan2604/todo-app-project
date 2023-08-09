package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TodoDto;
import com.nhantran.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/{taskId}")
    public ResponseEntity<TodoDto> createTodo( @RequestBody TodoDto userDto, @PathVariable Integer taskId) {
        return new ResponseEntity<>(todoService.createTodo(taskId,userDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Integer id) {
        return new ResponseEntity<>(todoService.updateTodo(todoDto,id), HttpStatus.OK);
    }
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Integer todoId) {
        return  new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
