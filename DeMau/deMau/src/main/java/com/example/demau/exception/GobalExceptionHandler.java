package com.example.demau.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,Object> errors = new HashMap<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        Map<String,Object> response = new HashMap<>();
        response.put("errors",errors);
        response.put("message", "Oi thoi chec, thieu du lieu roi !!!");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
