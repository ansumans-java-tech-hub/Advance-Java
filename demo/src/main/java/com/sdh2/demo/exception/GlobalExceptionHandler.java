package com.sdh2.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {/**
     * Handles IllegalArgumentException and returns a custom error response.
     *
     * @param ex the IllegalArgumentException thrown during application execution
     * @return a ResponseEntity containing an error message and a BAD_REQUEST (400) HTTP status
     */

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions and returns a custom error response.
     *
     * @param ex the Exception thrown during application execution
     * @return a ResponseEntity containing an error message and an INTERNAL_SERVER_ERROR (500) HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}