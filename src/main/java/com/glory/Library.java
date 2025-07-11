package com.glory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> listOfBooks = new ArrayList<>();
    private Map<Member, List<Book>> borrowedBookHistory = new HashMap<>();
    // this new hashMap<>(); shows that no member has borrowed anything yet.

    public Library(){
        borrowedBookHistory = new HashMap<>(); // what is this line doing exactly?
    }

    public void addBook(Book bk){
        listOfBooks.add(bk);
        System.out.println("A book was added to Library.");
    }

    public void removeBook(Book bk){
        listOfBooks.remove(bk);
        System.out.println("This book was removed from the library.");
    }

    public Book findBookByTitle(String title){
        for (Book b : listOfBooks){
            if (b.getTitle().equalsIgnoreCase(title)){
                return b;
            }

        }
        return null;
    }
    public void displayAllBooks(){
        for (Book b : listOfBooks){
            System.out.println(b.displayInfo());
            System.out.println("-------");

        }
    }

    public void BorrowedBookRecord (Member mem, Book buk) {
        borrowedBookHistory.computeIfAbsent(mem, placeHolder -> new ArrayList<>()).add(buk);
        System.out.println(mem.name + " borrowed " + buk.getTitle());
    }

    public void ReturnBookRecord(Member member, Book books){
        List<Book> borrowedBooks = borrowedBookHistory.get(member);
        if (borrowedBooks != null){
            borrowedBooks.remove(books);
            System.out.println(member.name + " returned " + books.getTitle());
        }
    }

    public void viewBorrowedBooks(Member member){
        List<Book> books = borrowedBookHistory.get(member);
        System.out.println("Borrowed books from " + member.name + ";");if (books != null && !books.isEmpty()){
            for (Book b : books){
                System.out.println("- " + b.getTitle());
            }
        } else {
            System.out.println(" - No books borrowed.");
        }
    }
}
