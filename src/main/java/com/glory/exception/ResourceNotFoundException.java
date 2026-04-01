package com.glory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404
// So @ResponseStatus annotation cause spring-boot to response with the specific HTTP status code whenever this exception is thown from your contoller.
public class ResourceNotFoundException extends RuntimeException{
    
    private String resourceName;
    private String fieldName;
    private String fieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;

    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName(){
        return fieldName;
    }

    public String getFieldValue(){

        return fieldValue;

    }

    public ResourceNotFoundException(String message){
        super(message);
    }
    


    /*  Simple method for this class:
    
    // @ResponseStatus(HttpStatus.NOT_FOUND) //404
    //public class ResourceNotFoundException extends RuntimeException{
    // public ResourceNotFoundException(String message){
    //     super(message);
    // }
    */


    

}
