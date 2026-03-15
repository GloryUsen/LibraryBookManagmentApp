package com.glory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glory.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
