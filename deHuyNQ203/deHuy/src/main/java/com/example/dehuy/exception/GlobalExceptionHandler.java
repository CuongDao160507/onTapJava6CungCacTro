package com.example.dehuy.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>>  handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,Object> errors = new HashMap<>();
        ex
                .getBindingResult()
                .getFieldErrors()
                .forEach((fieldError)->{
                    errors.put(fieldError.getField(),fieldError.getDefaultMessage());
                });
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errors,
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }
}
