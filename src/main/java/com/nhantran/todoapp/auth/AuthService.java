package com.nhantran.todoapp.auth;

import com.nhantran.todoapp.dto.LogInRequest;
import com.nhantran.todoapp.dto.RegisterRequest;
import com.nhantran.todoapp.entity.ERole;
import com.nhantran.todoapp.entity.Role;
import com.nhantran.todoapp.entity.Users;
import com.nhantran.todoapp.repository.RoleRepo;
import com.nhantran.todoapp.repository.UserRepo;
import com.nhantran.todoapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepo roleRepo;

    public AuthResponse register(RegisterRequest request) {

        Set<Role> listRoles = new HashSet<>();
        Set<String> roleFromRequest = request.getRole();
        if (roleFromRequest == null){
            Role userRole = roleRepo.findByName(ERole.USER).orElseThrow(()-> new RuntimeException("Role not found"));
            listRoles.add(userRole);
        }else {
            roleFromRequest.forEach(role -> {
                        if (role.equals("admin")) {
                            Role adminRole = roleRepo.findByName(ERole.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                            listRoles.add(adminRole);
                        } else if (role.equals("user")) {
                            Role userRole = roleRepo.findByName(ERole.USER).orElseThrow(()-> new RuntimeException("Role not found"));
                            listRoles.add(userRole);
                        }
                    });
        }
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(listRoles)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthResponse login(LogInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
