package com.nhantran.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhantran.todoapp.entity.Task;
import com.nhantran.todoapp.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

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
    private boolean done;
    @JsonIgnore
    private ZonedDateTime completedTime;
    @JsonIgnore
    private Users users;

    public static Task convertToTask(TaskDto taskDto) {
        Task task = new Task();

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
                .modifiedTime(task.getCreatedTime())
                .done(task.isDone())
                .completedTime(task.getCompletedTime())
                .build();
    }
}
