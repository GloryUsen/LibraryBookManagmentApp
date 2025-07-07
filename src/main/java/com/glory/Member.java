package com.glory;

import java.util.ArrayList;
import java.util.List;

public class Member extends BaseUser{

    private List<Book> borrowedBooks;

    public Member(String usernames, int userId) {
        super(usernames, userId);
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(String title, Library lib){
        Book bbs = lib.findBookByTitle(title);
        if (bbs != null && bbs.getIsAvailable()){
            borrowedBooks.add(bbs);
            bbs.borrowBook();
        } else {
            System.out.println("Book not available or not found.");
        }
    }

    public void returnBook(String title, Library lib){
        for (Book bbs : borrowedBooks){
            if (bbs.getTitle().equalsIgnoreCase(title)){
                bbs.returnBook();
                borrowedBooks.remove(bbs);
                return;
            }
        }

        System.out.println("You don't have this book. ");
    }
}
