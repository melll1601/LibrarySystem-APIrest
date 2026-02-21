package com.weg.LibrarySystem.mapper;

import com.weg.LibrarySystem.dto.book.BookRequestDto;
import com.weg.LibrarySystem.dto.book.BookResponseDto;
import com.weg.LibrarySystem.model.Book;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    public Book forEntity(BookRequestDto bookRequestDto){
        return new Book(
                bookRequestDto.title(),
                bookRequestDto.author(),
                bookRequestDto.yearPublication());
    }

    public BookResponseDto forResponseDto(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYearPublication());
    }

    public List<BookResponseDto> forResponseListDto(List<Book> books) throws SQLException{
        List<BookResponseDto> listResponseBook = new ArrayList<>();

        for (Book book : books){
            listResponseBook.add(forResponseDto(book));
        }
        return listResponseBook;
    }
}

