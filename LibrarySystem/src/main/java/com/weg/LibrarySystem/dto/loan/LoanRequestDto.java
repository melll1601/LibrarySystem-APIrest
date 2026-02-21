package com.weg.LibrarySystem.dto.loan;

import java.time.LocalDate;

public record LoanRequestDto(
        Long userId,
        Long bookId,
        LocalDate loanDate,
        LocalDate returnDate
) {
}
