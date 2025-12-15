package com.example.de01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        Map<String, Object> apiRespone =  new HashMap<>();
//        apiRespone.put("status", HttpStatus.BAD_REQUEST.value()); //khong can cung dc
        apiRespone.put("errors", errors);
        apiRespone.put("message", "Oi thoi chec, thieu du lieu roi.");
        return new ResponseEntity<>(apiRespone, HttpStatus.BAD_REQUEST);
    }
}
