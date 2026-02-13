package com.weg.LibrarySystem.controller;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.repository.BookRepository;
import com.weg.LibrarySystem.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/librarySystem/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public Book postBook(
            @RequestBody Book book
    ){
        try {
            book = bookService.registerBook(book);

        }catch (SQLException error){
            throw new RuntimeException(error);
        }

        return book;
    }

    @GetMapping
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();

        try {
            books = bookService.listBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @GetMapping("/{id}")
    public Book searchByIdBook(@PathVariable Long id) {
        try {
            return bookService.searchByIdBook(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book

    ) throws SQLException {
            book.setId(id);
            bookService.updateBook(id, book);

            return book;
    }
}
