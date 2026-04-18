package com.glory.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoanDto {

    private Long id;
    private Long userId;
    private Long bookId;
    
    private LocalDate borrowDate;
    private LocalDate returnDate;

    private boolean returned;

}
