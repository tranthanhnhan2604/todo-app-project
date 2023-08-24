package com.nhantran.todoapp.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex, WebRequest rq){
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ErrorResponse handleTaskNotFoundException(TaskNotFoundException ex, WebRequest rq){
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ErrorResponse handleTodoNotFoundException(TodoNotFoundException ex, WebRequest rq){
        return new ErrorResponse(ex.getMessage());
    }
}
