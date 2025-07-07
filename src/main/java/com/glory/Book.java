package com.glory;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private boolean bookIsAvailable;

    public Book(String title, String author, String isbn, boolean isAvailable){
//        this.title = title;  This validation was added to the constructor because the values were added directly inside the constructor.
//        this.author = author;
//        this.isbn = isbn;
//        this.isAvailable = isAvailable;
        setBookTitle(title);
        setBookAuthor(author);
        this.bookIsbn = isbn;
        this.bookIsAvailable = isAvailable;


    }

    public String getAuthor() {
        return bookAuthor;
    }

    public String getTitle(){
        return bookTitle;
    }

    public String getIsbn(){
        return bookIsbn;
    }

    public Boolean getIsAvailable(){
        return bookIsAvailable;
    }

    public void setBookTitle(String title){
        if (title == null || title.trim().isEmpty()){
            System.out.println("Title cannot be empty.");
            return;
        }
        this.bookTitle = title;
    }

    public void setBookAuthor(String author) {
        if (author == null || author.trim().isEmpty()){
            System.out.println("Author cannot be empty.");
            return;
        }
        this.bookAuthor = author;
    }

    public void setIsbn(String isbn){
        this.bookIsbn = isbn;
    }

    public void setAvailable(boolean isAvailable){
        this.bookIsAvailable = isAvailable;
    }

    public void borrowBook(){
        if (bookIsAvailable){
            System.out.println("You have successfully borrowed the book titled: "+ bookTitle);
            bookIsAvailable = false;
        } else {
            System.out.println(bookTitle + " is already borrowed! Check back later");
        }

    }
    public void returnBook(){
        if (!bookIsAvailable){
            System.out.println("You have successfully returned the book: "+ bookTitle );
            bookIsAvailable = true;
        } else {
            System.out.println(bookTitle + " was not borrowed");
        }
    }

    public String displayInfo(){
        return "Title: " + bookTitle + "\nAuthor: " + bookAuthor + "\nISBN: " +
                bookIsbn + "\nAvailable: " + bookIsAvailable;
    }

}
