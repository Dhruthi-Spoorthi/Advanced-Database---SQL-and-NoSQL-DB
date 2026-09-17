package com.metro.ride.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleConstraint(DataIntegrityViolationException ex) {
        return Map.of("error", "Ride cannot be deleted because it is linked to another record");
    }

    @ExceptionHandler({ IllegalArgumentException.class, org.springframework.dao.EmptyResultDataAccessException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handle(RuntimeException ex) {
        return Map.of("error", ex.getMessage() == null ? "Request failed" : ex.getMessage());
    }
}