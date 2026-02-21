package com.weg.LibrarySystem.dto.book;

public record BookRequestDto(
        String title,
        String author,
        Integer yearPublication) {
}
