package com.weg.LibrarySystem.dto.user;

public record UserResponseDto(
        Long id,
        String name,
        String email
) {
}
