package com.nhantran.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhantran.todoapp.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Integer id;

    private String name;

    @JsonIgnore
    private ZonedDateTime createdTime;

    @JsonIgnore
    private ZonedDateTime modifiedTime;

    @JsonIgnore
    private boolean done = false;

    @JsonIgnore
    private ZonedDateTime completedTime;

    @JsonIgnore
    private UserDto user;

    @JsonIgnore
    private List<TodoDto> todoList;

    public static Task convertToTask(TaskDto taskDto) {
        Task task = new Task();

        task.setUser(UserDto.convertToUser(taskDto.getUser()));
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setCreatedTime(taskDto.getCreatedTime());
        task.setModifiedTime(taskDto.getModifiedTime());
        task.setDone(taskDto.isDone());
        task.setCompletedTime(taskDto.getCompletedTime());

        return task;
    }

    public static TaskDto convertToTaskDto(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .createdTime(task.getCreatedTime())
                .modifiedTime(task.getModifiedTime())
                .done(task.isDone())
                .completedTime(task.getCompletedTime())
                .todoList(
                        task.getTodoList() != null ? task.getTodoList()
                                .stream()
                                .map(TodoDto::convertToTodoDto)
                                .collect(Collectors.toList()) : null
                )
                .build();
    }
}
