package com.glory.service;

import java.util.List;

import com.glory.dto.LoanRequestDto;
import com.glory.dto.LoanResponseDto;
import com.glory.dto.PageLoanResponse;

public interface LoanService {

    LoanResponseDto borrowBook(LoanRequestDto loanRequestDto);

    LoanResponseDto getLoanById(Long loanId);

    PageLoanResponse getAllLoans(int pageNo, int pageSize, String sortBy, String direction);

    LoanResponseDto returnBook(Long loanId);

    void deleteLoan(Long loanId);

}
