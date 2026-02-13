package com.weg.LibrarySystem.controller;

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
    public Loan postLoan(
            @RequestBody Loan loan
            ){

        try{
            loan = loanService.registerLoan(loan);

        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        return loan;
    }

    @GetMapping
    public List<Loan> getLoan(){
        List<Loan> loans = new ArrayList<>();

        try {
            loans = loanService.listLoan();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return loans;
    }

    @GetMapping("/{id}")
    public Loan searchByIdLoan(@PathVariable Long id) {
        try {
            return loanService.searchByIdLoan(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
