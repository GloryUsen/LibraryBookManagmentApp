package com.glory.service;

import com.glory.dto.BookRequestDto;
import com.glory.dto.BookResponseDto;
import com.glory.dto.PageBookResponse;

public interface BookService {

    BookResponseDto addBook (BookRequestDto bookRequestDto); 

    PageBookResponse getAllBooks(int pageNo, int pageSize, String sortBy);

    BookResponseDto getBookById(Long id);
    
    BookResponseDto updateBook(BookRequestDto bookRequestDto, long id);

    void deleteBookById(long id);

}
