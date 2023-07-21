package com.nhantran.todoapp.service;

import com.nhantran.todoapp.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto task);

    TaskDto updateTask(TaskDto task, Integer id);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Integer id);

    List<TaskDto> getAllTasksByUserId(Integer userId);

//    List<TaskDto> searchTask(String keyword);

    void markTaskIsDone(Integer id);

    void deleteTaskById(Integer id);

}
