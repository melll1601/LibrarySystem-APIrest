package com.weg.LibrarySystem.controller;

import com.weg.LibrarySystem.dto.loan.LoanRequestDto;
import com.weg.LibrarySystem.dto.loan.LoanResponseDto;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.repository.LoanRepository;
import com.weg.LibrarySystem.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/librarySystem/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanResponseDto postLoan(
            @RequestBody LoanRequestDto loanRequestDto
            ){

        try{
            return loanService.registerLoan(loanRequestDto);

        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }
    }

    @GetMapping
    public List<LoanResponseDto> getLoan(){
        List<Loan> loans = new ArrayList<>();

        try {
            return loanService.listLoan();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public LoanResponseDto searchByIdLoan(@PathVariable Long id) {
        try {
            return loanService.searchByIdLoan(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public LoanResponseDto updateLoan(
            @PathVariable Long id,
            @RequestBody LoanRequestDto loanRequestDto

    ) {

        try {
            return loanService.updateLoan(id, loanRequestDto);
        }catch (SQLException error){
            throw new RuntimeException(error.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public boolean deleteLoan(@PathVariable Long id) throws SQLException{
        return loanService.deleteLoan(id);
    }

    @GetMapping("/userloan/{id}")
    public List<LoanResponseDto> searchByUserIdLoan(@PathVariable Long id) {
        List<Loan> loans = new ArrayList<>();

        try {
            return loanService.searchByUserIdLoan(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
