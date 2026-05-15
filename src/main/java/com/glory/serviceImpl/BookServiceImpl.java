package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glory.dto.BookRequestDto;
import com.glory.dto.BookResponseDto;
import com.glory.dto.CategoryDto;
import com.glory.dto.PageBookResponse;
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
    public PageBookResponse getAllBooks(int pageNo, int pageSize, String sortBy) {

      // Pageable pageable = PageRequest.of(pageNo, pageSize);
      Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));


      //Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        // List<Book> books =  bookRepository.findAll();
        Page<Book> books = bookRepository.findAllWithCategory(pageable);

        List<Book> listOfBooks = books.getContent();

             // return books.stream().map(this::mapBookEntityToBookResponse).collect(Collectors.toList());
            //  return listOfBooks.stream().map(this::mapBookEntityToBookResponse).collect(Collectors.toList());
             List<BookResponseDto> bookContent = listOfBooks.stream()
             .map(this::mapBookEntityToBookResponse).collect(Collectors.toList());

             PageBookResponse response = new PageBookResponse();
             response .setBookContent(bookContent);
             response.setPageSize(books.getSize());
             response.setPageNo(books.getNumber());
             response.setPageElements(books.getTotalElements());
             response.setTotalPage(books.getTotalPages());
             response.setLast(books.isLast());

             return response;
            }

    private Book mapBookRequestDtoToEntity(BookRequestDto dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
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