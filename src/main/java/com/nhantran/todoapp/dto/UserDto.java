package com.nhantran.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhantran.todoapp.entity.Role;
import com.nhantran.todoapp.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private Set<Role> role;

    public static Users convertToUser(UserDto userDto) {
        final Users users = new Users();

        users.setId(userDto.getId());
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
//        users.setTask(
//                userDto.getTask() != null ? userDto.getTask()
//                        .stream()
//                        .map(TaskDto::convertToTask)
//                        .collect(Collectors.toList()) : null
//        );

        return users;
    }

    public static UserDto convertToUserDto(Users users){
        return UserDto.builder()
                .id(users.getId())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .password(users.getPassword())
                .role(users.getRole())
//                .task(
//                        users.getTask() != null ? users.getTask()
//                                .stream()
//                                .map(TaskDto::convertToTaskDto)
//                                .collect(Collectors.toList()) : null
//                )
                .build();
    }
}
