package com.nhantran.todoapp.service.impl;

import com.nhantran.todoapp.dto.TaskDto;
import com.nhantran.todoapp.entity.Task;
import com.nhantran.todoapp.entity.Users;
import com.nhantran.todoapp.exception.TaskNotFoundException;
import com.nhantran.todoapp.exception.UserNotFoundException;
import com.nhantran.todoapp.repository.TaskRepo;
import com.nhantran.todoapp.repository.UserRepo;
import com.nhantran.todoapp.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;


    @Override
    public TaskDto createTask(TaskDto taskDto, Integer id) {
        Task task = new Task();
        task.setName((taskDto.getName()));
        task.setCreatedTime(ZonedDateTime.now());
        task.setModifiedTime(ZonedDateTime.now());
        task.setDone(false);
        task.setCompletedTime(null);

        Users userId = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("userId not found"));
        task.setUser(userId);

        return TaskDto.convertToTaskDto(taskRepo.save(task));
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Integer id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not be found"));
        task.setName(taskDto.getName());
        task.setModifiedTime(ZonedDateTime.now());
        return TaskDto.convertToTaskDto(taskRepo.save(task));
    }


    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepo.findAll()
                .stream()
                .map(TaskDto::convertToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Integer id) {
        return taskRepo.findById(id)
                .map(TaskDto::convertToTaskDto)
                .orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not be found"));
    }

    @Override
    public List<TaskDto> getAllTasksByUserId(Integer id) {
        List<Task> tasksList = taskRepo.findTasksByUserId(id);
        return tasksList.stream()
                .map(TaskDto::convertToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public void markTaskIsDone(Integer id) {
        if (id == null) {
            log.error("Task id is null");
            return;
        }
        Task task = taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id = " + id + " not be found"));
        task.setDone(true);
        task.setCompletedTime(ZonedDateTime.now());
        task.setModifiedTime(ZonedDateTime.now());
        TaskDto.convertToTaskDto(taskRepo.save(task));
    }


    @Override
    public void deleteTaskById(Integer id) {
        if (id == null) {
            log.error("Task id is null");
            return;
        }
        taskRepo.deleteById(id);
    }

}
