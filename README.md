# Library Management App

A professional library management system built with Java, designed to manage books and student borrowing efficiently. This project demonstrates role-based access,
book management,
borrow/return functionality.

FEATURES

ADMIN (A Librarian)
- Add new books to the library
- Remove books from the inventory
- View all books with status (available/borrowed)
- Track borrowed books and student borrowers

STUDENTS
- Search books by title
- Borrow available books (max 3 at a time)
- Return borrowed books
- View currently borrowed books

SYSTEM RULES
- Duplicate books cannot be added
- Borrowed books are indicated in the student's account
- Role-based access ensures only authorized actions are allowed

TECHNOLOGY STACK
- Core Java (current version)
- Spring Boot (planned migration)
- Spring Data JPA (for database management)
- Spring Security + JWT (for authentication and role management)
- MySQL / PostgreSQL (database)
- Maven (project management)
