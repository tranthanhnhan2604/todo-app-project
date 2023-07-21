package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TaskDto;
import com.nhantran.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")

public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer id) {
        return new ResponseEntity<>(taskService.updateTask(taskDto,id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/done")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateTaskStatus(@PathVariable Integer id) {
        taskService.markTaskIsDone(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskDto>> getAllTasksByUserId(@PathVariable Integer id){
        return new ResponseEntity<>(taskService.getAllTasksByUserId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
