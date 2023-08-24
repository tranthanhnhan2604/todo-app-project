package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TaskDto;
import com.nhantran.todoapp.dto.TodoDto;
import com.nhantran.todoapp.entity.Task;
import com.nhantran.todoapp.exception.TaskNotFoundException;
import com.nhantran.todoapp.repository.TaskRepo;
import com.nhantran.todoapp.repository.UserRepo;
import com.nhantran.todoapp.security.CustomUserDetailsService;
import com.nhantran.todoapp.service.TaskService;
import com.nhantran.todoapp.service.TodoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private TaskRepo taskRepo;

    @PostMapping("/")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal UserDetails userLoggedIn) {
        String userName = userLoggedIn.getUsername();
        Integer userId = customUserDetailsService.loadIdByUsername(userName);
        return new ResponseEntity<>(taskService.createTask(taskDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer id) {
        return new ResponseEntity<>(taskService.updateTask(taskDto,id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/done")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Integer id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not be found"));
        if (task.isDone()) {
            return ResponseEntity.badRequest().body("The task id = " + id +" has been done, can't be marked again");
        }
        taskService.markTaskIsDone(id);
        return ResponseEntity.ok().body("Task is done!");
    }

    @GetMapping("/{id}/todos")
    public ResponseEntity<List<TodoDto>> getAllTodosByTaskId(@PathVariable Integer id) {
        return new ResponseEntity<>(todoService.getAllTodoByTaskId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
