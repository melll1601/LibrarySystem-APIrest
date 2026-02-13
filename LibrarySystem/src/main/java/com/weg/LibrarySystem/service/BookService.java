package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BookService{

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book registerBook(Book book) throws SQLException {

        try {
            bookRepository.registerBook(book);
            return book;

        }catch (SQLException error){
            throw new RuntimeException("Error registering book", error);
        }
    }
}
