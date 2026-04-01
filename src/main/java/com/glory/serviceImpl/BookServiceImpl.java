package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.glory.dto.BookDto;
import com.glory.dto.CategoryDto;
import com.glory.entity.Book;
import com.glory.entity.Category;
import com.glory.exception.ResourceNotFoundException;
import com.glory.repository.BookRepository;
import com.glory.repository.CategoryRepository;
import com.glory.service.BookService;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
    Book book = mapBookDtoToEntity(bookDto);
    Book savedBook = bookRepository.save(book);

    return mapBookEntityToDto(savedBook);


    }


    private BookDto mapDtoToEntity1(BookDto dto){

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailable(dto.isAvailable());
        book.setStatus(dto.getStatus());

        if(dto.getCategory() != null){
            Category category = categoryRepository.findById(dto.getCategory().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
            book.setCategory(category);
        }

        return dto;
}

        private Book mapBookDtoToEntity(BookDto dto){

            Book book = new Book();

            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setIsbn(dto.getIsbn());
            book.setAvailable(dto.isAvailable());
            book.setStatus(dto.getStatus());

            if(dto.getCategory() != null){
                Category category = categoryRepository.findById(dto.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

                book.setCategory(category);
            }

            return book;
        }





    @Override
    public List<BookDto> getAllBooks() {

        List<Book> books = bookRepository.findAll();
        return books.stream()
          .map(this::mapBookEntityToDto)
          .collect(Collectors.toList());
    }

    

        private Book mapDtoToEntity(BookDto dto){

            Book books = new Book();

            books.setId(dto.getId());
            books.setTitle(dto.getTitle());
            books.setAuthor(dto.getAuthor());
            books.setIsbn(dto.getIsbn());
            books.setAvailable(dto.isAvailable());
            books.setStatus(dto.getStatus());


            if(dto.getCategory() != null){
                Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

                books.setCategory(category);

            }

            return books;

    }




        private BookDto mapBookEntityToDto(Book book){

        BookDto dto = new BookDto();

        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setAvailable(book.isAvailable());
        dto.setStatus(book.getStatus());

        if(book.getCategory() != null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(book.getCategory().getId());
            categoryDto.setName(book.getCategory().getName());
            dto.setCategory(categoryDto);
        }

        return dto;
    }


}

