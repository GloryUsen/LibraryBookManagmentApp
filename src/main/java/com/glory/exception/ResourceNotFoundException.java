package com.glory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404
// So @ResponseStatus annotation cause spring-boot to response with the specific HTTP status code whenever this exception is thown from your contoller.
public class ResourceNotFoundException extends RuntimeException{
    
    private String resourceName;
    private String fieldName;
    private long fieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, Long id){
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, id));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = id;

    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName(){
        return fieldName;
    }

    public long getFieldValue(){

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
