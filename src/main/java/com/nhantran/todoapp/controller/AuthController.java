package com.nhantran.todoapp.controller;

import com.nhantran.todoapp.auth.AuthResponse;
import com.nhantran.todoapp.auth.AuthService;
import com.nhantran.todoapp.dto.LogInRequest;
import com.nhantran.todoapp.dto.RegisterRequest;
import com.nhantran.todoapp.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    @Autowired
    private UserRepo userRepo;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        AuthResponse result = service.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogInRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
