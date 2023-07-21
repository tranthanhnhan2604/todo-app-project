package com.nhantran.todoapp.exception;

public enum ErrorStatus {
    USER_NOT_FOUND(1000),
    TASK_NOT_FOUND(1001),
    TODO_NOT_FOUND(1002)
    ;

    private final int value;

    ErrorStatus(int value) {
        this.value = value;
    }

}
