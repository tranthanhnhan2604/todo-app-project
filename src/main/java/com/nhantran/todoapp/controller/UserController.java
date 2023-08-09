package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.dto.TaskDto;
import com.nhantran.todoapp.dto.UserDto;
import com.nhantran.todoapp.service.TaskService;
import com.nhantran.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @PathVariable Integer id) {
        return new ResponseEntity<>(userService.updateUser(user,id), HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskDto>> getAllTasksByUserId(@PathVariable Integer id){
        return new ResponseEntity<>(taskService.getAllTasksByUserId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
