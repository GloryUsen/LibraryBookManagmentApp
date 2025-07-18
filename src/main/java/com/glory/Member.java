package com.glory;

import java.util.Objects;

public class Member extends BaseUser{

    public Member(String usernames, int userId) {
        super(usernames, userId);
    }

    public void borrowBook(String title, Library lab){
        Book books = lab.findBookByTitle(title);
        if (books != null && books.getIsAvailable()){
            lab.recordBorrowedBook(this, books);
            books.borrowBook();
        } else {
            System.out.println("Book not found or already borrowed.");
        }
    }


    public void returnBook(String title, Library keeper) {
        Book book = keeper.findBookByTitle(title);

        if (book != null) {
            keeper.recordReturnedBook(this, book);
            book.returnBook();
        } else {
            System.out.println(" Book not found. ");
        }
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())  return false;
        Member aMember = (Member) obj;
        return id == aMember.id && name.equalsIgnoreCase(aMember.name);
    }

        @Override
        public int hashCode () {
            return Objects.hash(name.toLowerCase(), id);

    }
}
