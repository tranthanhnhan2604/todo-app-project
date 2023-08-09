package com.nhantran.todoapp.service;

import com.nhantran.todoapp.dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto updateUser(UserDto user, Integer id);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);

    void deleteUserById(Integer id);
}
