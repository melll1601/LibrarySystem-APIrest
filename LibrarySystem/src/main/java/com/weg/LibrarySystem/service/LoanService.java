package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.repository.BookRepository;
import com.weg.LibrarySystem.repository.LoanRepository;
import com.weg.LibrarySystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class LoanService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public LoanService(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public Loan registerLoan(Loan loan) throws SQLException{

        try {

            if (bookRepository.bookExists(loan.getBookId()) && userRepository.userExists(loan.getUserId())){
                loanRepository.registerLoan(loan);
            }else {
                throw new RuntimeException("Error registering loan");
            }

        }catch (SQLException error){
            error.printStackTrace();
        }

        return loan;
    }
}
