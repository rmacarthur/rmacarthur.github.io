package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooks();

    Book getBookByTitle (String title);

    Book getBookByIsbn (String isbn);

    Book getBookByAuthor (String author);

    void addBook(Book bookToAdd);

    void removeBook(String isbn);

}
