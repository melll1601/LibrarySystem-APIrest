package com.weg.LibrarySystem.dto.loan;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record LoanRequestDto(
        @Positive(message = "[THE ID MUST BE POSITIVE]")
        @NotNull
        Long userId,
        @Positive(message = "[THE ID MUST BE POSITIVE]")
        @NotNull
        Long bookId,
        @Past(message = "[THE LOAN CANNOT BE FOR THE FUTURE]")
        @NotNull
        LocalDate loanDate,
        @Future(message = "[RETURNS CANNOT BE PROCESSED IN THE PAST]" )
        @NotNull
        LocalDate returnDate
) {
}
