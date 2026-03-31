package com.foodapp.exception;

import com.foodapp.dto.NotificationResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public NotificationResponse handleException(Exception ex) {
        return new NotificationResponse("ERROR", ex.getMessage());
    }
}