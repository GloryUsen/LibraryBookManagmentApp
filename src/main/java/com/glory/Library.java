package com.glory;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> listOfBooks = new ArrayList<>();

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
}
