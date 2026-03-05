package com.weg.LibrarySystem.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "[THE NAME CANNOT BE EMPTY]")
        String name,
        @Email(message = "[INVALID EMAIL]")
        @NotBlank(message = "[THE EMAIL CANNOT BE EMPTY]")
        String email) {
}
