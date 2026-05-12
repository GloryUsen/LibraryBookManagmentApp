package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.glory.dto.BookRequestDto;
import com.glory.dto.BookResponseDto;
import com.glory.dto.CategoryDto;
import com.glory.entity.Book;
import com.glory.entity.Category;
import com.glory.enums.BookStatus;
import com.glory.exception.ResourceNotFoundException;
import com.glory.repository.BookRepository;
import com.glory.repository.CategoryRepository;
import com.glory.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {

        Category category = categoryRepository.findById(bookRequestDto.getCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException("category", "id", bookRequestDto.getCategoryId()));


        Book book = mapBookRequestDtoToEntity(bookRequestDto);
        book.setCategory(category);
        Book newBook = bookRepository.save(book);



        // BookRequestDto bookResponseDto = mapBookEntityToDto(newBook);
        return mapBookEntityToBookResponse(newBook);
    }

    

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapBookEntityToBookResponse)
                .collect(Collectors.toList());
    }

    private Book mapBookRequestDtoToEntity(BookRequestDto dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());

        book.setAvailable(true);
        book.setStatus(BookStatus.AVAILABLE);

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category",
                            "id",
                            dto.getCategoryId()
));

            book.setCategory(category);
        }

        return book;
    }

    private BookResponseDto mapBookEntityToBookResponse(Book book) {
        BookResponseDto dto = new BookResponseDto();

        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setIsAvailable(book.isAvailable());
        dto.setStatus(book.getStatus());

         if (book.getCategory() != null) {
             CategoryDto categoryDto = new CategoryDto();
             categoryDto.setId(book.getCategory().getId());
             categoryDto.setName(book.getCategory().getName());

             dto.setCategory(categoryDto);
        }

        return dto;
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        return mapBookEntityToBookResponse(book);
    }

    @Override
    public BookResponseDto updateBook(BookRequestDto bookRequestDto, long id) {

       
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        book.setTitle(bookRequestDto.getTitle());


        if (bookRequestDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(bookRequestDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category",
                            "id",
                            bookRequestDto.getCategoryId()));

            book.setCategory(category);
        }

        Book updatedBook = bookRepository.save(book);
        return mapBookEntityToBookResponse(updatedBook);
    }

    @Override
    public void deleteBookById(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        bookRepository.delete(book);
    }
}