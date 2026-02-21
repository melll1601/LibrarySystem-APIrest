package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.dto.loan.LoanRequestDto;
import com.weg.LibrarySystem.dto.loan.LoanResponseDto;
import com.weg.LibrarySystem.mapper.LoanMapper;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.repository.BookRepository;
import com.weg.LibrarySystem.repository.LoanRepository;
import com.weg.LibrarySystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LoanService {

    private final LoanMapper loanMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public LoanService(LoanMapper loanMapper, BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.loanMapper = loanMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public LoanResponseDto registerLoan(LoanRequestDto loanRequestDto) throws SQLException {

        Loan loan = loanMapper.forEntity(loanRequestDto);
        validateBookIsNotLoaned(loan.getBookId());

        return loanMapper.forResponseDto(
                loanRepository.registerLoan(loan)
        );
    }

    public void validateBookIsNotLoaned(Long bookId) throws SQLException {
        if (loanRepository.existsOpenLoanByBookId(bookId)) {
            throw new RuntimeException("Book is already loaned");
        }
    }

    public List<Loan> listLoan() throws SQLException{
        return loanRepository.listLoan();
    }

    public Loan searchByIdLoan(Long id) throws SQLException{

        if (loanRepository.loanExists(id)){
            return loanRepository.searchByIdLoan(id);
        }else {
            throw new RuntimeException("ID does not exist");
        }
    }

    public List<Loan> searchByUserIdLoan(Long userId) throws SQLException {
        List<Loan> loans = loanRepository.searchByUserIdLoan(userId);

        if (loans.isEmpty()) {
            throw new RuntimeException("User ID does not exist or has no loans");
        }

        return loans;
    }

    public void updateLoan(Long id, Loan loan) throws SQLException{

        if (loanRepository.loanExists(id)){
            loan.setId(id);
            loanRepository.updateLoan(id, loan);

        }else {
            throw new RuntimeException("ID does not exist");
        }
    }

    public boolean deleteLoan(Long id) throws SQLException {

        if (loanRepository.loanExists(id)){
            return loanRepository.deleteLoan(id);

        }else {
            throw new RuntimeException("ID does not exist");
        }
    }


}
