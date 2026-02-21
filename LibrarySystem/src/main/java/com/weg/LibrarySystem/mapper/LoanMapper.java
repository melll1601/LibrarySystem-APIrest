package com.weg.LibrarySystem.mapper;

import com.weg.LibrarySystem.dto.loan.LoanRequestDto;
import com.weg.LibrarySystem.dto.loan.LoanResponseDto;
import com.weg.LibrarySystem.model.Loan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<LoanResponseDto> forResponseListDto(List<Loan> loans){
        List<LoanResponseDto> listResponseLoan = new ArrayList<>();

        for (Loan loan : loans){
            listResponseLoan.add(forResponseDto(loan));
        }

        return listResponseLoan;
    }
}
