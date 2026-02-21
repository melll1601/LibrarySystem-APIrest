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
    public Loan searchByIdLoan(@PathVariable Long id) {
        try {
            return loanService.searchByIdLoan(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Loan updateLoan(
            @PathVariable Long id,
            @RequestBody Loan loan

    ) throws SQLException {
        loan.setId(id);
        loanService.updateLoan(id, loan);

        return loan;
    }

    @DeleteMapping("/{id}")
    public boolean deleteLoan(@PathVariable Long id) throws SQLException{
        return loanService.deleteLoan(id);
    }

    @GetMapping("/userloan/{id}")
    public List<Loan> searchByUserIdLoan(@PathVariable Long id) {
        List<Loan> loans = new ArrayList<>();

        try {
            loans = loanService.searchByUserIdLoan(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return loans;
    }

}
