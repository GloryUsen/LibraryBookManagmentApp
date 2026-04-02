package com.glory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.BookDto;
import com.glory.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto){
        BookDto savedBook = bookService.addBook(bookDto);
        return new ResponseEntity<>(savedBook,  HttpStatus.CREATED);
        
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
        
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(bookService.getBookById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable(name = "id") long id){
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
