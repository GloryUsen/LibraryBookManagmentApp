package com.glory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> listOfBooks = new ArrayList<>();
    private Map<Member, List<Book>> borrowedBookHistory;

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

    public void recordOfBooksBorrowed (Member mem, Book buk) {
        borrowedBookHistory.computeIfAbsent(mem, placeHolder -> new ArrayList<>()).add(buk);
        System.out.println(mem.name + " borrowed " + buk.getTitle());
    }

    public void recordOfBooksReturned(Member member, Book books){
        List<Book> book1 = borrowedBookHistory.get(member);
        if (book1 != null){
            book1.remove(books);
            System.out.println(member.name + " return " + books.getTitle());
        }
    }

    public void viewBorrowedBooks(Member member){
        List<Book> books = borrowedBookHistory.get(member);
        System.out.println("Borrowed books from " + member.name + " ; ");
        if (books != null && !books.isEmpty()){
            for (Book b : books){
                System.out.println("- " + b.getTitle());
            }
        } else {
            System.out.println(" - No books borrowed.");
        }
    }
}
