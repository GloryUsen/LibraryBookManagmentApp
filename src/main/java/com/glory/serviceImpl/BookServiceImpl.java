package com.glory.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.glory.entity.Book;
import com.glory.entity.Role;
import com.glory.entity.User;
import com.glory.enums.BookStatus;
import com.glory.repository.BookRepository;
import com.glory.service.BookService;

@Service

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    
    public BookerviceImpl(BookRepository bookRepository, ModelMapper modelMapper){

        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Book addBook(User user, Book book) {
        if(user.getRole() != Role.ADMIN){

        throw new ForbiddenException("Only Adim can add books");// can i replac ResourceNotFoundException here?

        }

       BookValidator.validate(book);

       Optional<Book> existingBook = bookRepository
       .findByTitleAndAUthorAndIsbn(book.getTitle(), book.getAuthor(), book.getIsbn());

       if(existingBook.isPresent()) {
        throw new DuplicateBookException(
            String.format("Book already exists: %s by %s (ISBN: %s)",
            book.getTitle(), book.getAuthor(), book.getIsbn()));
    }
    book.setStatus(BookStatus.AVAILABLE);
    return bookRepository.save(book);
}

}
