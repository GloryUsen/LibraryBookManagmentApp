package com.glory;

public class Admin extends BaseUser{

    public Admin(String username, int userid) {
        super(username, userid);
    }

    public void addBook(Book aBook, Library lab){
        lab.addBook(aBook);
        System.out.println(name + " added: " + aBook.getTitle());

    }

    public void removeBook(Book aBook, Library lab){
        lab.removeBook(aBook);
        System.out.println(name + " remove: " + aBook.getTitle());
    }
}
