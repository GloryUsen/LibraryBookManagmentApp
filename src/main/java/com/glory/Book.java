package com.glory;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn, boolean isAvailable){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle(){
        return title;
    }

    public String getIsbn(){
        return isbn;
    }

    public Boolean getIsAvailable(){
        return isAvailable;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void borrowBook(){
        if (isAvailable){
            System.out.println("You have successfully borrowed the book titled: "+ title);
            isAvailable = false;
        } else {
            System.out.println(title + " is already borrowed! Check back later");
        }

    }
    public void returnBook(){
        if (!isAvailable){
            System.out.println("You have successfully returned the book: "+ title );
            isAvailable = true;
        } else {
            System.out.println(title + " was not borrowed");
        }
    }

    public String displayInfo(){
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " +
                isbn + "\nAvailable: " + isAvailable;
    }

}
