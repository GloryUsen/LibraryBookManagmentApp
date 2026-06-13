package com.glory.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glory.dto.LoanRequestDto;
import com.glory.dto.LoanResponseDto;
import com.glory.dto.PageLoanResponse;
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
    public LoanResponseDto borrowBook(LoanRequestDto loanRequestDto) {

        Loan loan = new Loan();

        User user = userRepository.findById(loanRequestDto.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", loanRequestDto.getUserId()));

        Book book = bookRepository.findById(loanRequestDto.getBookId())
        .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loanRequestDto.getBookId()));

        loan.setUser(user);
        loan.setBook(book);
        loan.setReturned(false);

        Loan savedLoan = loanRepository.save(loan);

        return mapLoanEntityToLoanResponseDto(savedLoan);

        
    }

    @Override
    public LoanResponseDto getLoanById(Long loanId){

        Loan loanExist = loanRepository.findById(loanId)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        return mapLoanEntityToLoanResponseDto(loanExist);
        
    }



    @Override
    public PageLoanResponse getAllLoans(int pageNo, int pageSize, String sortBy, String direction){
        
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Loan> loans = loanRepository.findAll(pageable);

        List<LoanResponseDto> contents = loans.getContent()
                .stream()
                .map(this::mapLoanEntityToLoanResponseDto)
                .collect(Collectors.toList());

        PageLoanResponse response = new PageLoanResponse();
        response.setContent(contents);
        response.setPageNo(loans.getNumber());
        response.setPageSize(loans.getSize());
        response.setTotalElements(loans.getTotalElements());
        response.setTotalPages(loans.getTotalPages());
        response.setLast(loans.isLast());

        return response;
    }

    @Override
    public LoanResponseDto returnBook(Long loanId){
        Loan returnLoan = loanRepository.findById(loanId)

        .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        returnLoan.setReturned(true);
        returnLoan.setReturnedDate(LocalDate.now());

        Loan updatedLoan = loanRepository.save(returnLoan);

        return mapLoanEntityToLoanResponseDto(updatedLoan);
    }



    @Override
    public void deleteLoan (Long loanId){

        Loan deleting = loanRepository.findById(loanId)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        loanRepository.delete(deleting);
        
         
    }



    //Mapping Entity To Dto Method

    
    public LoanResponseDto mapLoanEntityToLoanResponseDto(Loan loanEntity){

        LoanResponseDto dto = mapper.map(loanEntity, LoanResponseDto.class);

        if(loanEntity.getUser() != null){
            dto.setUserId(loanEntity.getUser().getId());
        }

        if(loanEntity.getBook() != null){
            dto.setBookId(loanEntity.getBook().getId());
        }

        return dto;
        
    }
}
