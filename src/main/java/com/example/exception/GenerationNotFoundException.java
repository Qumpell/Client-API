package com.example.exception;

import lombok.Getter;
import org.aspectj.bridge.IMessage;

import java.time.LocalDateTime;

@Getter
public class GenerationNotFoundException extends RuntimeException{
    private final LocalDateTime errorTime;
    private final String errorDetails;

    public GenerationNotFoundException(String message, String errorDetails) {
        super(message);
        this.errorTime = LocalDateTime.now();
        this.errorDetails = errorDetails;
    }
}
