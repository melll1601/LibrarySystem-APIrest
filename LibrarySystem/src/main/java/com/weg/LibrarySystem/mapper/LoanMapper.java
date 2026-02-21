package com.weg.LibrarySystem.mapper;

import com.weg.LibrarySystem.dto.loan.LoanRequestDto;
import com.weg.LibrarySystem.dto.loan.LoanResponseDto;
import com.weg.LibrarySystem.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan forEntity(LoanRequestDto loanRequestDto){
        return new Loan(
                loanRequestDto.userId(),
                loanRequestDto.bookId(),
                loanRequestDto.loanDate(),
                loanRequestDto.returnDate()
        );
    }

    public LoanResponseDto forResponseDto(Loan loan){
        return new LoanResponseDto(
                loan.getId(),
                loan.getUserId(),
                loan.getBookId(),
                loan.getLoanDate(),
                loan.getReturnDate()
        );
    }
}
