package com.glory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.LoanDto;
import com.glory.service.LoanService;

@RestController
@RequestMapping("/api/loans")

public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<LoanDto> borrowBook(@RequestBody LoanDto loanDto){
        System.out.println("🔥 Loan API HIT");
       LoanDto loanBook =  loanService.borrowBook(loanDto);
        return new ResponseEntity<>(loanBook, HttpStatus.CREATED);
        
    }

}
