package com.glory.service;

import java.util.List;

import com.glory.dto.BookDto;

public interface BookService {

    BookDto addBook (BookDto book); 

    List<BookDto> getAllBooks();

}
