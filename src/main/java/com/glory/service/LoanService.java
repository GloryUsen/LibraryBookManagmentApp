package com.glory.service;

import java.util.List;

import com.glory.dto.LoanDto;

public interface LoanService {

    LoanDto borrowBook(LoanDto loanDto);

    LoanDto getLoanById(Long loanId);

    List<LoanDto> getAllLoans();

    LoanDto returnBook(Long loanId);

    void deleteLoan(Long loanId);

}
