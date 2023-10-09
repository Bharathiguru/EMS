package com.example.employeemanagementsystem.customException;

import com.example.employeemanagementsystem.resonse.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.TypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse fieldValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ApiResponse.builder().code("1005").errors(errors).build();
    }
    
    @ExceptionHandler(TypeMismatchException.class)
    public ApiResponse misMatch(TypeMismatchException ex){
        return ApiResponse.builder().code("1005").errors(Map.of("error", "invalid type")).build();
    }
}
