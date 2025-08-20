package com.nora.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errorDetails = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();

            Map<String, String> fieldError = new HashMap<>();
            fieldError.put("message", errorMessage);

            errorDetails.add(fieldError);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", "VALIDATION_ERROR");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errorDetails);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", "INTERNAL_ERROR");
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
