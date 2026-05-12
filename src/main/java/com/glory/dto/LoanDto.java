package com.glory.dto;

import java.time.LocalDate;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;


// @Data
// @NoArgsConstructor
// @AllArgsConstructor

public class LoanDto {

    private Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }


    private Long userId;

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }


    private Long bookId;

    public Long getBookId(){
        return bookId;
    }

    public void setBookId(Long bookId){
        this.bookId = bookId;
    }

    
    private LocalDate borrowDate;

    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate){
        this.borrowDate = borrowDate;
    }


    private LocalDate returnDate;

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }


    private boolean returned;

    public boolean isReturned(){
        return returned;
    }

    public void setReturned(boolean returned){
        this.returned = returned;
    }

}
