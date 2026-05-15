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
       // System.out.println("🔥 Loan API HIT");
       LoanDto loanBook =  loanService.borrowBook(loanDto);
        return new ResponseEntity<>(loanBook, HttpStatus.CREATED);
        
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable Long loanId){
        LoanDto loan = loanService.getLoanById(loanId);

        return ResponseEntity.ok(loan);

    }

    @GetMapping
    public ResponseEntity <List<LoanDto>> getAllLoans(){
        List<LoanDto> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);


    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<LoanDto> returnBook(@PathVariable Long loanId){

        LoanDto returnBook = loanService.returnBook(loanId);
        return ResponseEntity.ok(returnBook);
        
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId){

        loanService.deleteLoan(loanId);
        return ResponseEntity.ok("Loan deleted successfully");


    } 


}
