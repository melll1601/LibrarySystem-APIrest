package com.weg.LibrarySystem.dto.book;

public record BookResponseDto(
        Long id,
        String title,
        String author,
        Integer yearPublication
) {
}
