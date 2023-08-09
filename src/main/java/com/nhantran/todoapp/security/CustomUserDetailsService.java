package com.nhantran.todoapp.security;

import com.nhantran.todoapp.entity.Users;
import com.nhantran.todoapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(userEmail).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    public Integer loadIdByUsername(String userEmail) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(userEmail).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return user.getUserId();
    }

}
