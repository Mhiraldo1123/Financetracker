package com.marvin.financetracker.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    HttpStatus statusCode;
    LocalDateTime time;
    String message;

    public ErrorResponse(HttpStatus statusCode, LocalDateTime time, String message){
        this.statusCode = statusCode;
        this.time = time;
        this.message = message;
    }


}
