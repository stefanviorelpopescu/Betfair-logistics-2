package org.digitalstack.logistics.controller;

import jakarta.validation.ConstraintViolationException;
import org.digitalstack.logistics.exception.DateRangeException;
import org.digitalstack.logistics.exception.DestinationNotFoundException;
import org.digitalstack.logistics.exception.InvalidDestinationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({ConstraintViolationException.class, DateRangeException.class, InvalidDestinationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(Exception ex) {
        return new ResponseEntity<>("Bad request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DestinationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleDestinationNotFoundException(DestinationNotFoundException exception) {

    }
}
