package com.marvin.financetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException e){
        //System.out.println("Error message: " + e.getMessage() + LocalDateTime.now());
        ErrorResponse e1 = new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<ErrorResponse>(e1, e1.getStatusCode());

    }
}
