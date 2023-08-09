package com.nhantran.todoapp.service.impl;

import com.nhantran.todoapp.dto.UserDto;
import com.nhantran.todoapp.entity.Users;
import com.nhantran.todoapp.exception.UserNotFoundException;
import com.nhantran.todoapp.repository.UserRepo;
import com.nhantran.todoapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        Users users = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not be found"));
        users.setFirstName(userDto.getFirstName());
        users.setLastName(users.getLastName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        return UserDto.convertToUserDto(userRepo.save(users));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserDto::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        if (id == null) {
            log.error("User id is null");
            return null;
        }
        return userRepo.findById(id)
                .map(UserDto::convertToUserDto)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not be found"));
    }

    @Override
    public void deleteUserById(Integer id) {
        if (id == null) {
            log.error("User id is null");
        }
        userRepo.deleteById(id);
    }

}
