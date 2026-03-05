package com.weg.LibrarySystem.dto.book;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookRequestDto(
        @NotBlank(message = "[THE NAME CANNOT BE EMPTY]")
        String title,
        @NotBlank(message = "[THE NAME CANNOT BE EMPTY]")
        String author,
        @Positive
        @Max(value = 2026, message = "THE YEAR OF PUBLICATION CANNOT BE LATER THAN THE CURRENT YEAR")
        @NotNull(message = "[THE NAME CANNOT BE EMPTY]")
        Integer yearPublication) {
}
