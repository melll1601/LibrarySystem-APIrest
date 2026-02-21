package com.weg.LibrarySystem.controller;
import com.weg.LibrarySystem.dto.book.BookRequestDto;
import com.weg.LibrarySystem.dto.book.BookResponseDto;
import com.weg.LibrarySystem.model.Book;
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
    public BookResponseDto postBook(
            @RequestBody BookRequestDto bookRequestDto
            ){
        try {
           return bookService.registerBook(bookRequestDto);

        }catch (SQLException error){
            throw new RuntimeException(error);
        }
    }

    @GetMapping
    public List<BookResponseDto> getBooks(){

        try {
            return bookService.listBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable Long id) throws SQLException{
       return bookService.deleteBook(id);
    }

}
