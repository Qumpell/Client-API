package com.example.exception;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class EntityNotFoundException extends RuntimeException {
    private final LocalDateTime errorTime;
    private final String errorDetails;

    public EntityNotFoundException(String message, String errorDetails) {
        super(message);
        this.errorTime = LocalDateTime.now();
        this.errorDetails = errorDetails;
    }
}
