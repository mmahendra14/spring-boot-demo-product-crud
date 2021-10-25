package com.example.demoproductcrudexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e){
          return respond(e, "No Data Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorMessage> handleConstriantVoilationException(ConstraintViolationException e){
     return  e.getConstraintViolations().stream().map(constraintViolation -> constraintViolation.getMessage() + "for "
             +constraintViolation.getPropertyPath()).map(message -> new ErrorMessage("Validation Error", message)).collect(Collectors.toList());
    }

    public ResponseEntity<ErrorMessage> respond(Exception e, String error, HttpStatus status){
        ErrorMessage msg = new ErrorMessage(error, e.getLocalizedMessage());
        return ResponseEntity.status(status).body(msg);
    }
}
