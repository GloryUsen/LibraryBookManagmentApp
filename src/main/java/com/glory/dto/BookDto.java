package com.glory.dto;

import com.glory.enums.BookStatus;

import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private BookStatus status; 
    private CategoryDto category;


}
