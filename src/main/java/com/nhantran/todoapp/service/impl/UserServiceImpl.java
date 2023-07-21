package com.nhantran.todoapp.service.impl;

import com.nhantran.todoapp.dto.UserDto;
import com.nhantran.todoapp.entity.User;
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
    public UserDto createUser(UserDto user) {
        return UserDto.convertToUserDto(
                userRepo.save(
                        UserDto.convertToUser(user)
                )
        );
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not be found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(user.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return UserDto.convertToUserDto(userRepo.save(user));
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
