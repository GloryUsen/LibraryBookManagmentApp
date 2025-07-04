package com.glory;


import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Book> bookList = new ArrayList<>(); // creates an Array of emptyList


        while (true) { // Asking a user repeatedly how many books to add.
            System.out.println("Title:"); //
            String title = input.nextLine();

            System.out.println("Author:");
            String auth = input.nextLine();

            System.out.println("ISBN: ");
            String isbn = input.nextLine();



            Book newBook = new Book(title, auth, isbn, true);
            if (newBook.getTitle() != null && newBook.getAuthor() != null) {
                bookList.add(newBook);
                System.out.println("Book added. ");
            } else {
                System.out.println("Book not added due to invalid data.");
            }

            System.out.println("Do you want to add another book?(yes/no): ");
            String respond = input.nextLine().trim().toLowerCase();
            if (!respond.equals("yes")) break;


        }

        while (true){
            System.out.println("Do you want to borrow or return a book? (borrow/return/exit):");
            String books = input.nextLine().trim().toLowerCase();

            if (books.equals("exit")) break;

            System.out.println("Enter the book title");
            String theTitle = input.nextLine();

            boolean bookFound = false;

            for (Book book : bookList){
                if (book.getTitle().equalsIgnoreCase(theTitle)){
                    bookFound = true;
                    if (books.equals("borrow")){
                        book.borrowBook();
                    } else if(books.equals("return")){
                        book.returnBook();
                    } else {
                        System.out.println("Invalid Action");
                    }
                    break;
                }

            }
            if (!bookFound){
                System.out.println("Book titled '" + theTitle + "' not found." );
            }

        }

        System.out.println("Current Library:");

        for (Book book : bookList) {
            System.out.println(book.displayInfo());
            //book.displayInfo();
            System.out.println("-----------");
        }
        input.close();

    }

}



