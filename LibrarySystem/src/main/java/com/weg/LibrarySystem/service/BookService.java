package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.dto.book.BookRequestDto;
import com.weg.LibrarySystem.dto.book.BookResponseDto;
import com.weg.LibrarySystem.mapper.BookMapper;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService{

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookResponseDto registerBook(BookRequestDto bookRequestDto) throws SQLException {
        Book book = bookMapper.forEntity(bookRequestDto);
        return bookMapper.forResponseDto(bookRepository.registerBook(book));
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

    public Boolean deleteBook(Long id) throws SQLException {

        if (bookRepository.bookExists(id)){
            return bookRepository.deleteBook(id);

        }else {
            throw new RuntimeException("ID does not exist");
        }
    }
}
