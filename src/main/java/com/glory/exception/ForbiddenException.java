package com.glory.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter

@ResponseStatus(HttpStatus.FORBIDDEN) // 403
public class ForbiddenException extends RuntimeException{

    // public ForbiddenException(String message){
    //     super(message);
    // }


    private String action;
    private String resource;

    public void DuplicateBookException(String action, String resource){
        //super(String.format("You are not allow to %s this %s", action, resource));
        this.action = action;
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
