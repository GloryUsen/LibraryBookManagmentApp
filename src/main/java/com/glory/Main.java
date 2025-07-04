package com.glory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        // How to print out values in Array.
//
//        String[] myName = new String[6];
//        myName[0] = "Grace";
//        myName[1] = "glory";
//        myName[2] = "glory";
//        myName[3] = "glory";
//        myName[4] = "glory";
//        myName[5] = "glory";
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        System.out.println(myName.length);
//        System.out.println(Arrays.toString(myName));
//












        int[] obj = new int[4];
        obj[0]= 1;
        obj[1] = 2;
        obj[2] = 10;
        obj[3] = 5;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::");

        System.out.println(obj.length);
        System.out.println(Arrays.toString(obj));

        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


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
            bookList.add(newBook);

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



    // MY FIRST IMPLEMENTATION B4 SCANNER


//
//        Scanner newScan = new Scanner(System.in);
//
//        ArrayList<Book> numberOfBooks = new ArrayList<>();
//
//        Book bk = new Book("The Alchemist", "Paulo Coelho", "978-0-545-01022-1",
//                true);
//
//        System.out.println("Title: " + bk.getTitle());
//        System.out.println("Author: " + bk.getAuthor());
//        System.out.println("ISBN: " + bk.getIsbn());
//        System.out.println("Available: " + bk.getIsAvailable());
//
//
//        System.out.println(bk.displayInfo());
//
//
//            System.out.println("");
//
//            Book book1 = new Book("Book1", " auth1", "3453665252", true);
//
//
//            Book book2 = new Book("Book2", " auth2", "3453665252", true);
//
//            Book book3 = new Book("Book3", " auth3", "3453665252", true);
//
//            Book book4 = new Book("Book4", " auth4", "3453665252", true);
//
//            Book book5 = new Book("Book5", " ", "3453665252", true);
//
//            Book book6 = new Book("Book6", " ", "3453665252", true);
//
//            Book book7 = new Book("Book7", " ", "3453665252", true);
//
//            Book book8 = new Book("Book8", " ", "3453665252", true);
//
//            Book book9 = new Book("Book9", " ", "3453665252", true);
//
//            Book book10 = new Book("Boo10", " ", "3453665252", true);
//
//            System.out.println(bk.displayInfo());
//            System.out.println();
//            System.out.println(book1.displayInfo());
//            System.out.println();
//            System.out.println(book2.displayInfo());
//            System.out.println();
//            System.out.println(book3.displayInfo());
//            System.out.println();
//            System.out.println(book4.displayInfo());
//            System.out.println();
//            System.out.println(book5.displayInfo());
//            System.out.println();
//            System.out.println(book6.displayInfo());
//            System.out.println();
//            System.out.println(book7.displayInfo());
//            System.out.println();
//            System.out.println(book8.displayInfo());
//            System.out.println();
//            System.out.println(book9.displayInfo());
//            System.out.println();
//            System.out.println(book10.displayInfo());
//
//
//        while (true) {
//            System.out.println("Title");
//            String title = newScan.nextLine();
//
//            System.out.println("Author");
//            String author = newScan.nextLine();
//
//            System.out.println("ISBN");
//            String isbn = newScan.nextLine();
//
//
//            for (Book book : numberOfBooks){
//               bk.displayInfo();
//            }
//            newScan.close();
//
//        }
//
//    }
//
//
//}