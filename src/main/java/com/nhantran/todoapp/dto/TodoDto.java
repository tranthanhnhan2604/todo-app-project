package com.nhantran.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhantran.todoapp.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

    private Integer id;

    private String title;
    @JsonIgnore
    private ZonedDateTime createdTime;
    @JsonIgnore
    private ZonedDateTime modifiedTime;
    private Integer taskId;

    public static Todo convertToTodo(TodoDto todoDto){

        final Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setCreatedTime(todoDto.getCreatedTime());
        todo.setModifiedTime(todoDto.getModifiedTime());
        return todo;
    }


    public static TodoDto convertToTodoDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .createdTime(todo.getCreatedTime())
                .modifiedTime(todo.getModifiedTime())
                .taskId(todo.getTask().getId())
                .build();
    }

}
