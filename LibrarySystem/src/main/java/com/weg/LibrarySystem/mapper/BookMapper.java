package com.weg.LibrarySystem.mapper;

import com.weg.LibrarySystem.dto.book.BookRequestDto;
import com.weg.LibrarySystem.dto.book.BookResponseDto;
import com.weg.LibrarySystem.model.Book;
import org.springframework.stereotype.Component;

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
}

