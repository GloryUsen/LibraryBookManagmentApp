package com.glory;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner execution = new Scanner(System.in);

        Library lab = new Library();


        Admin admin = new Admin("Sarah", 1);
        Member member = new Member("John", 2);
        member.displayInfo();


        while (true) {
            System.out.println("Who are you? (Admin/Member/Exit):");
            String role = execution.nextLine().trim().toLowerCase();


            if (role.equalsIgnoreCase("exit")) break;

            if (role.equalsIgnoreCase("admin")) {
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
                    } else if (adminAction.equalsIgnoreCase("remove")) {
                        admin.removeBook(bk, lab);
                    } else {
                        System.out.println("Invalid admin action. ");
                    }
                } else {
                    System.out.println("Book not added due to invalid data.");
                }


            } else if (role.equalsIgnoreCase("member")) {
                System.out.println("Borrow, Return or View borrowed books? (borrow/return/view): ");
                String memberAction = execution.nextLine().trim().toLowerCase();

                if (memberAction.equalsIgnoreCase("view")) {
                    lab.viewBorrowedBooks(member);
                    continue;
                }

                System.out.println("Enter book title: ");
                String title1 = execution.nextLine();


                if (memberAction.equalsIgnoreCase("borrow")) {
                    member.borrowBook(title1, lab);
                } else if (memberAction.equalsIgnoreCase("return")) {
                    member.returnBook(title1, lab);
                } else {
                    System.out.println("Invalid member action.");
                }

            } else {
                System.out.println("Invalid role.");
            }

            System.out.println("\n Current Books Library: ");
            lab.displayAllBooks();


            String catalogFile = "catalog.csv";
            String historyFile = "history.csv";
            Library library = Library.loadFromFiles(catalogFile, historyFile);

            library.saveCatalog(catalogFile);
            library.saveHistory(historyFile);

        }
    }



    }




