package com.glory;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> listOfBooks = new ArrayList<>();
    private Map<Member, List<Book>> borrowedBookHistory = new HashMap<>();
    // this new hashMap<>(); shows that no member has borrowed anything yet.


    public Library() {
        borrowedBookHistory = new HashMap<>(); // what is this line doing exactly?
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
        System.out.println(" Book added to library. " + book.getTitle());
    }

    public void removeBook(Book book) {
        listOfBooks.remove(book);
        System.out.println(" Book removed from the library. " + book.getTitle());
    }

    public Book findBookByTitle(String title) {
        for (Book book : listOfBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }

        }
        return null;
    }

    public Book findBookByIsbn(String isbn){
        for (Book book : listOfBooks){
            if (book.getIsbn().equalsIgnoreCase(isbn)){
                return book;
            }
        }
        return null;
    }



    public void displayAllBooks() {
        for (Book b : listOfBooks) {
            System.out.println(b.displayInfo());
            System.out.println("-------");

        }
    }

    public void recordBorrowedBook(Member oneMember, Book oneBook) {
        borrowedBookHistory.computeIfAbsent(oneMember, placeHolder -> new ArrayList<>()).add(oneBook);
        System.out.println(oneMember.getName() + " borrowed " + oneBook.getTitle());
    }

    public void recordReturnedBook(Member oneMember, Book oneBook) {
        List<Book> borrowedBooks = borrowedBookHistory.get(oneMember);
        if (borrowedBooks != null && borrowedBooks.remove(oneBook)) {
            System.out.println(oneMember.getName() + " returned: " + oneBook.getTitle());
        }
    }


    public void viewBorrowedBooks(Member viewBooks) {
        List<Book> books = borrowedBookHistory.getOrDefault(viewBooks, new ArrayList<>());
        viewBooks.displayInfo();
        System.out.println("Borrowed books from " + viewBooks.getName() + ":");

        if (books == null || !books.isEmpty()) {
            System.out.println(" - No books borrowed.");
        } else {
            for (Book book : books) {
                System.out.println("- " + book.getTitle());
            }
        }

    }

    public void printLine(String str){
        System.out.println(str);
    }

    private Optional<Book> findAvailable(String bookTitle){
        return listOfBooks.stream().filter(book ->book.getTitle().equalsIgnoreCase(bookTitle) && book.getIsAvailable())
                .findFirst();
    }

    public void saveCatalog(String savePath){
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(savePath))){
            for (Book book : listOfBooks){
                writer.write(String.join(",",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getIsAvailable() ? "1" : "0"));
                writer.newLine();
            }
            System.out.println("Catalog saved to " + savePath);
        } catch (IOException e){
            System.out.println("Failed to save catalog: " + e.getMessage());
        }
    }

    public void saveHistory(String historyPath){
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(historyPath))){
            for (Map.Entry<Member, List<Book>> entryPath : borrowedBookHistory.entrySet()){
                Member member1 = entryPath.getKey();
                String isbns = entryPath.getValue().stream()
                        .map(Book::getIsbn)
                        .collect(Collectors.joining(";"));
                writer.write(member1.getId() + "," + member1.getName() + "," + isbns);
                writer.newLine();
            }
            System.out.println("History save to " + historyPath);
        } catch (IOException e){
            System.out.println("Failed to save history: " + e.getMessage());
        }
    }



    public static Library loadFromFiles(String catalogPath, String historyPath2){
        Library lab = new Library();
        try(BufferedReader reading = Files.newBufferedReader(Paths.get(catalogPath))){
            String line;
            while ((line = reading.readLine()) != null){
                String[] parts = line.split(",");
                Book bookie = new Book(parts[0], parts[1], parts[2], true);
                bookie.setAvailable("1".equals(parts[4]));
                lab.addBook(bookie);
            }
        } catch (IOException e){
            System.out.println("Could not load catalog: " + e.getMessage());

        }

        try(BufferedReader r = Files.newBufferedReader(Paths.get(historyPath2))){
            String line2;
            while ((line2 = r.readLine()) != null){
                String[] parts = line2.split(",", 4);
                int memberId = Integer.parseInt(parts[0]);
                String memberName = parts[1];
                Member members = new Member(memberName, memberId);
                String[] isbns = parts[2].split(",");
                for (String isbn : isbns){
                    Book book = lab.findBookByIsbn(isbn);
                    if (book != null){
                        lab.recordBorrowedBook(members, book);
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Could not load history: " + e.getMessage());
        }
        return lab;
    }
}
