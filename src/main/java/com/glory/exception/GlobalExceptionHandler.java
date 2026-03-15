package com.glory.exception;

import com.glory.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.glory.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception error, HttpStatus status){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(error.getMessage());
        response.setStatus(status.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, status);

    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException singleError){
        return buildErrorResponse(singleError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException singleError){
        return buildErrorResponse(singleError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateBookException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateBook(DuplicateBookException singleError){
            return buildErrorResponse(singleError, HttpStatus.CONFLICT);
        }
    }
    

