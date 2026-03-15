package com.glory.exception;
import com.glory.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404
public class ResourceNotFoundException extends RuntimeException{
    
    private String resourceName;
    private String fieldName;
    private String fieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;


    /*  Simple method for this class:
    
    // @ResponseStatus(HttpStatus.NOT_FOUND) //404
    //public class ResourceNotFoundException extends RuntimeException{
    // public ResourceNotFoundException(String message){
    //     super(message);
    // }
    */


    }

}
