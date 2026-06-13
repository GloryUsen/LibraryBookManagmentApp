package com.glory.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.BookRequestDto;
import com.glory.dto.BookResponseDto;
import com.glory.dto.PageBookResponse;
import com.glory.service.BookService;
import com.glory.utils.AppConstants;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto bookDto){
        BookResponseDto savedBook = bookService.addBook(bookDto);
        return new ResponseEntity<>(savedBook,  HttpStatus.CREATED);
        
    }

    @GetMapping
    public PageBookResponse getAllBooks(

        // @RequestParam(value = "PageNo", defaultValue = "0", required = false) int pageNo,
        // @RequestParam(value = "PageSize", defaultValue = "10", required = false) int pageSize,
        // @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy



        @RequestParam(value = "PageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
        @RequestParam(value = "PageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_PAGE_DIRECTION) String direction

    ){
        return bookService.getAllBooks(pageNo, pageSize, sortBy, direction);
        
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(bookService.getBookById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookRequestDto bookDto, @PathVariable(name = "id") long id){
       // BookDto bookResponse =  bookService.updateBook(bookDto, id);
        //return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        return ResponseEntity.ok(bookService.updateBook(bookDto, id));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") long id){
          bookService.deleteBookById(id);
        return ResponseEntity.ok("Book successfully deleted");

    }


    

}
