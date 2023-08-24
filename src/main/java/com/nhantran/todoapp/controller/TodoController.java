package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TodoDto;
import com.nhantran.todoapp.entity.Task;
import com.nhantran.todoapp.exception.TaskNotFoundException;
import com.nhantran.todoapp.repository.TaskRepo;
import com.nhantran.todoapp.service.TodoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@SecurityRequirement(name = "bearerAuth")
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private TaskRepo taskRepo;

    @PostMapping("/{taskId}")
    public ResponseEntity<?> createTodo(@RequestBody TodoDto userDto, @PathVariable Integer taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with id = " + taskId + " not be found"));
        if (task.isDone()) {
            return ResponseEntity.badRequest().body("The task id = " + taskId +" has been done, can't be added more todos");
        }
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
    public ResponseEntity<?> deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
