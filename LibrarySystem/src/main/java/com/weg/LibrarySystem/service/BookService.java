package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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

    public List<Book> listBooks() throws SQLException{
        return bookRepository.listBooks();
    }

    public Book searchByIdBook(Long id) throws SQLException{

        if (bookRepository.bookExists(id)){
            return bookRepository.searchByIdBook(id);
        }else {
            throw new RuntimeException("ID does not exist");
        }
    }
    public void updateBook(Long id, Book book) throws SQLException{

        if (bookRepository.bookExists(id)){
            book.setId(id);
            bookRepository.updateBook(id, book);
        }else {
            throw new RuntimeException("ID does not exist");
        }
    }
}
