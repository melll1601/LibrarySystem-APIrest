package com.weg.LibrarySystem.dto.loan;

import java.time.LocalDate;

public record LoanResponseDto(
        Long id,
        Long userId,
        Long bookId,
        LocalDate loanDate,
        LocalDate returnDate
) {
}
