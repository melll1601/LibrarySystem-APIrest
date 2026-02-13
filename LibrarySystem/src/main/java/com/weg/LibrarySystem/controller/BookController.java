package com.weg.LibrarySystem.controller;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.repository.BookRepository;
import com.weg.LibrarySystem.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
}
