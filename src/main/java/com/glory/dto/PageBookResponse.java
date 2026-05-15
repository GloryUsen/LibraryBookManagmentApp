package com.glory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class PageBookResponse {

    private List<BookResponseDto> bookContent;
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private long pageElements;
    private boolean last;

    public List<BookResponseDto> getBookContent() {
        return bookContent;
    }

    public void setBookContent(List<BookResponseDto> bookContent) {
        this.bookContent = bookContent;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getPageElements() {
        return pageElements;
    }

    public void setPageElements(long pageElements) {
        this.pageElements = pageElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }


}
