package com.glory.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.glory.dto.LoanDto;
import com.glory.entity.Book;
import com.glory.entity.Loan;
import com.glory.entity.User;
import com.glory.exception.ResourceNotFoundException;
import com.glory.repository.BookRepository;
import com.glory.repository.LoanRepository;
import com.glory.repository.UserRepository;
import com.glory.service.LoanService;

@Service

public class LoanServiceImpl implements LoanService{


    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private ModelMapper mapper;

    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, ModelMapper mapper){
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;

    }

    @Override
    public LoanDto borrowBook(LoanDto loanDto) {

        Loan loan = new Loan();

        User user = userRepository.findById(loanDto.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", loanDto.getUserId()));

        Book book = bookRepository.findById(loanDto.getBookId())
        .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loanDto.getBookId()));

        loan.setUser(user);
        loan.setBook(book);
        loan.setReturned(false);

        Loan savedLoan = loanRepository.save(loan);

        return mapLoanEntityToLoanDto(savedLoan);

        
    }

    @Override
    public LoanDto getLoanById(Long loanId){

        Loan loanExist = loanRepository.findById(loanId)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        return mapLoanEntityToLoanDto(loanExist);
        
    }



    @Override
    public List<LoanDto>  getAllLoans(){
        List<Loan> loans = loanRepository.findAll();

        return loans.stream()
        .map(this::mapLoanEntityToLoanDto).collect(Collectors.toList());

    }

    @Override
    public LoanDto returnBook(Long loanId){
        Loan returnLoan = loanRepository.findById(loanId)

        .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        returnLoan.setReturned(true);
        returnLoan.setReturnedDate(LocalDate.now());

        Loan updatedLoan = loanRepository.save(returnLoan);

        return mapLoanEntityToLoanDto(updatedLoan);
    }



    @Override
    public void deleteLoan (Long loanId){

        Loan deleting = loanRepository.findById(loanId)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        loanRepository.delete(deleting);
        
         
    }



    //Mapping Entity To Dto Method

    
    public LoanDto mapLoanEntityToLoanDto(Loan loanEntity){

        LoanDto dto = mapper.map(loanEntity, LoanDto.class);

        // LoanDto dto = new LoanDto();
        
        // dto.setId(loanEntity.getId());
        // dto.setBorrowDate(loanEntity.getBorrowDate());
        // dto.setReturnDate(loanEntity.getReturnedDate());
        // dto.setReturned(loanEntity.isReturned());

        if(loanEntity.getUser() != null){
            dto.setUserId(loanEntity.getUser().getId());
        }

        if(loanEntity.getBook() != null){
            dto.setBookId(loanEntity.getBook().getId());
        }


        return dto;
        
    }
}
