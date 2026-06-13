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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.LoanRequestDto;
import com.glory.dto.LoanResponseDto;
import com.glory.dto.PageLoanResponse;
import com.glory.service.LoanService;
import com.glory.utils.AppConstants;

@RestController
@RequestMapping("/api/loans")

public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<LoanResponseDto> borrowBook(@RequestBody LoanRequestDto loanRequestDto){
       LoanResponseDto loanBook =  loanService.borrowBook(loanRequestDto);
        return new ResponseEntity<>(loanBook, HttpStatus.CREATED);
        
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponseDto> getLoanById(@PathVariable Long loanId){
        LoanResponseDto loan = loanService.getLoanById(loanId);

        return ResponseEntity.ok(loan);

    }

    @GetMapping
    public ResponseEntity<PageLoanResponse> getAllLoans(
        @RequestParam(value = "PageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
        @RequestParam(value = "PageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_PAGE_DIRECTION) String direction
    ){
        return ResponseEntity.ok(loanService.getAllLoans(pageNo, pageSize, sortBy, direction));
    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<LoanResponseDto> returnBook(@PathVariable Long loanId){

        LoanResponseDto returnBook = loanService.returnBook(loanId);
        return ResponseEntity.ok(returnBook);
        
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId){

        loanService.deleteLoan(loanId);
        return ResponseEntity.ok("Loan deleted successfully");


    } 


}
