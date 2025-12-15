package com.example.ontapdemau.exception;

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
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,Object> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError)->{
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        Map<String,Object> apiResponse = new HashMap<>();
        apiResponse.put("data",errors);
        apiResponse.put("message", "Oi thoi chec, thieu du lieu roi !");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
