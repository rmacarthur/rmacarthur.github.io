package com.techelevator.controller;


import com.techelevator.dao.BookDao;
import com.techelevator.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BooksController {

    @Autowired
    BookDao bookDao;

    @GetMapping(path = {"/allBooks"})
    public List<Book> getAllBooks() {
        logTimestamp("Getting all books");

        return bookDao.getAllBooks();
    }

    @GetMapping("/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        logTimestamp("Returning " + title);
        Book aBook = bookDao.getBookByTitle(title);

        return aBook;
    }

    @PostMapping("/addBook")
    public void addBook (@RequestBody @Valid Book newBook) {
        logTimestamp("Adding new book " + newBook.getTitle());
        bookDao.addBook(newBook);
    }

    @DeleteMapping("removeBook/{isbn}")
    public void deleteBook(@PathVariable String isbn){
        bookDao.removeBook(isbn);
        logTimestamp("Removing book " + isbn);
    }

    static void logTimestamp (String msg) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + " at " + timestamp);
    }

}
