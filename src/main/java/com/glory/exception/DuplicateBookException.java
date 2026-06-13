package com.glory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;


@Getter

@ResponseStatus(HttpStatus.CONFLICT) //409
public class DuplicateBookException extends RuntimeException{


    // public DuplicateBookException(String message){
    //     super(message);
    // }

    private String isbn;

    public DuplicateBookException(String isbn){
        super(String.format("Book already exists with ISBN : '%s'", isbn));
        this.isbn = isbn;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }



}
