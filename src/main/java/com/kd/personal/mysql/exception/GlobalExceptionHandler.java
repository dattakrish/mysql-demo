package com.kd.personal.mysql.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    private ErrorDetails errorDetails(Exception ex, WebRequest request){
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());
        return errorMessage(errorMessages,request);
    }

    private ErrorDetails errorMessage(List<String> errorMessages, WebRequest request) {
        String details = getDetails(request);
        return new ErrorDetails(new Date(),errorMessages,details);
    }

    private String getDetails(WebRequest request) {
        return request.getDescription(false);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request){
        return new ResponseEntity<>(errorDetails(ex,request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(errorDetails(ex,request),HttpStatus.NOT_FOUND);
    }
}
