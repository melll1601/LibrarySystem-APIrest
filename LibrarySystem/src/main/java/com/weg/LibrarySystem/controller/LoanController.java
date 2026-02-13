package com.weg.LibrarySystem.controller;

import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.repository.LoanRepository;
import com.weg.LibrarySystem.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
}
