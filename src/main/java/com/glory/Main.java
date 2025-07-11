package com.glory;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner execution = new Scanner(System.in);
        // ArrayList<Book> bookList = new ArrayList<>(); // creates an Array of emptyList

        Library lab = new Library();
        Admin admin = new Admin("Sarah:", 1);
        Member member = new Member("John:", 2);


        while (true) {
            System.out.println("Who are you? (Admin/Member/Exit):");
            String role = execution.nextLine().trim().toLowerCase();


            if (role.equals("exit")) {
                break;
            }

            if (role.equals("admin")) {
                System.out.println("Add or Remove a book? (add/remove):");
                String adminAction = execution.nextLine().trim().toLowerCase();


                System.out.println("Enter book title: ");
                String title = execution.nextLine();

                System.out.println("Enter book author: ");
                String auth = execution.nextLine();

                System.out.println("Enter book isbn: ");
                String isbn = execution.nextLine();

                Book bk = new Book(title, auth, isbn, true);

                if (bk.getTitle() != null && bk.getAuthor() != null && bk.getIsbn() != null) {
                    if (adminAction.equalsIgnoreCase("add")) {
                        admin.addBook(bk, lab);
                    } else if (adminAction.equalsIgnoreCase("remove")){
                        admin.removeBook(bk, lab);
                    } else {
                        System.out.println("Invalid admin action. ");
                    }
                } else {
                    System.out.println("Book not added due to invalid data.");
                }


                } else if (role.equals("member")) {
                    System.out.println("Borrow, Return or View borrowed books? (borrow/return/view): ");
                    String memberAction = execution.nextLine().trim().toLowerCase();

                    if (memberAction.equalsIgnoreCase("view")){
                        lab.viewBorrowedBooks(member);
                        return;
                    }

                    System.out.println("Enter book title: ");
                    String title1 = execution.nextLine();



                    if (memberAction.equalsIgnoreCase("borrow")) {
                        member.borrowBook(title1, lab);
                    } else if (memberAction.equals("return")) {
                        member.returnBook(title1, lab);
                    } else {
                        System.out.println("Invalid member action.");
                    }

                } else {
                    System.out.println("Invalid role.");
                }

                System.out.println("\n Current Books Library: ");
                lab.displayAllBooks();

            }

            execution.close();
            System.out.println("Program exited");
        }
    }



//        while (true) { // Asking a user repeatedly how many books to add.
//            System.out.println("Title:"); //
//            String title = execution.nextLine();
//
//            System.out.println("Author:");
//            String auth = execution.nextLine();
//
//            System.out.println("ISBN: ");
//            String isbn = execution.nextLine();
//

//
//            Book newBook = new Book(title, auth, isbn, true);
//            if (newBook.getTitle() != null && newBook.getAuthor() != null) {
//                execution.add(newBook);
//                System.out.println("Book added. ");
//            } else {
//                System.out.println("Book not added due to invalid data.");
//            }
//
//            System.out.println("Do you want to add another book?(yes/no): ");
//            String response = execution.nextLine().trim().toLowerCase();
//            if (!response.equals("yes")) break;
//
//        }
//
//        while (true){
//            System.out.println("Do you want to borrow or return a book? (borrow/return/exit):");
//            String books = execution.nextLine().trim().toLowerCase();
//
//            if (books.equals("exit")) break;
//
//            System.out.println("Enter the book title");
//            String theTitle = execution.nextLine();
//
//            boolean bookFound = false;
//
//            for (Book book : bookList){
//                if (book.getTitle().equalsIgnoreCase(theTitle)){
//                    bookFound = true;
//                    if (books.equals("borrow")){
//                        book.borrowBook();
//                    } else if(books.equals("return")){
//                        book.returnBook();
//                    } else {
//                        System.out.println("Invalid Action");
//                    }
//                    break;
//                }
//
//            }
//            if (!bookFound){
//                System.out.println("Book titled '" + theTitle + "' not found." );
//            }
//
//        }
//
//        System.out.println("Current Library:");
//
//        for (Book book : bookList) {
//            System.out.println(book.displayInfo());
//            //book.displayInfo();
//            System.out.println("-----------");
//        }
//        execution.close();
//
//    }
//
//}
//
//            }
//


