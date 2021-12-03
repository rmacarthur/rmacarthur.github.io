package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookJdbcDao implements BookDao {

    private JdbcTemplate template;

    public BookJdbcDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books";
        SqlRowSet result = template.queryForRowSet(sql);
        while(result.next()){
            bookList.add(mapResultToBook(result));
        }
        return bookList;
    }

    @Override
    public Book getBookByTitle(String title) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE title ILIKE ?";
        SqlRowSet result = template.queryForRowSet(sql, title);
        if (result.next()) {
            book = mapResultToBook(result);
        }
        return book;
    }

    @Override
    public Book getBookByIsbn (String isbn) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE isbn = ?";
        SqlRowSet result = template.queryForRowSet(sql, isbn);
        if (result.next()) {
            book = mapResultToBook(result);
        }
        return book;
    }

    @Override
    public Book getBookByAuthor (String author) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE author = ?";
        SqlRowSet result = template.queryForRowSet(sql, author);
        if (result.next()) {
            book = mapResultToBook(result);
        }
        return book;

    }
    @NotNull
    @Override
    public void addBook(Book bookToAdd) {
        String sql = "INSERT INTO books (title, author) "
                + "values (?, ?)";
        template.update(sql, bookToAdd.getTitle(), bookToAdd.getAuthor());

    }

    @Override
    public void removeBook(String isbn) {
        String sql = "delete from books where isbn = ?";
        template.update(sql, isbn);
    }

    private Book mapResultToBook(SqlRowSet result){

        String isbn = result.getString("isbn");
        String title = result.getString("title");
        String description = result.getString("description");
        String author = result.getString("author");

        Book book = new Book(isbn, title,  author, description);
        return book;
    }
}
