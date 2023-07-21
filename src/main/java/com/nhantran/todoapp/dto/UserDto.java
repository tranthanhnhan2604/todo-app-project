package com.nhantran.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhantran.todoapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    @JsonIgnore
    private List<TaskDto> task;

    public static User convertToUser(UserDto userDto) {
        final User user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setTask(
                userDto.getTask() != null ? userDto.getTask()
                        .stream()
                        .map(TaskDto::convertToTask)
                        .collect(Collectors.toList()) : null
        );

        return user;
    }

    public static UserDto convertToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .task(
                        user.getTask() != null ? user.getTask()
                                .stream()
                                .map(TaskDto::convertToTaskDto)
                                .collect(Collectors.toList()) : null
                )
                .build();
    }
}
