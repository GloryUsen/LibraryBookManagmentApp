package com.glory.service;

import java.util.List;

import com.glory.dto.BookRequestDto;
import com.glory.dto.BookResponseDto;

public interface BookService {

    BookResponseDto addBook (BookRequestDto bookRequestDto); 

    List<BookResponseDto> getAllBooks();

    BookResponseDto getBookById(Long id);
    
    BookResponseDto updateBook(BookRequestDto bookRequestDto, long id);

    void deleteBookById(long id);

}
