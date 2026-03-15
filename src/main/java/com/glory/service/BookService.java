package com.glory.service;

import com.glory.entity.Book;
import com.glory.entity.User;

public interface BookService {
    Book addBook(User user, Book book);

}
