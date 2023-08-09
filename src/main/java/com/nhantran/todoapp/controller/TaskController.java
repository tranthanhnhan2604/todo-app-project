package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TaskDto;
import com.nhantran.todoapp.dto.TodoDto;
import com.nhantran.todoapp.repository.UserRepo;
import com.nhantran.todoapp.security.CustomUserDetailsService;
import com.nhantran.todoapp.service.TaskService;
import com.nhantran.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")

public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal UserDetails userLoggedIn) {
        String userName = userLoggedIn.getUsername();
        Integer userId = customUserDetailsService.loadIdByUsername(userName);
        return new ResponseEntity<>(taskService.createTask(taskDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer id) {
        return new ResponseEntity<>(taskService.updateTask(taskDto,id), HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/done")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity updateTaskStatus(@PathVariable Integer id) {
        taskService.markTaskIsDone(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/todos")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<List<TodoDto>> getAllTodosByTaskId(@PathVariable Integer id) {
        return new ResponseEntity<>(todoService.getAllTodoByTaskId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
