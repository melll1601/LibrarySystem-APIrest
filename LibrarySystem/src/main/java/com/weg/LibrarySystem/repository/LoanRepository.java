package com.weg.LibrarySystem.repository;

import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.util.ConnectionMysql;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LoanRepository {

    public Loan registerLoan(Loan loan) throws SQLException{

        String query = """
                INSERT INTO Loan
                (bookId, userId, loanDate, returnDate)
                VALUES
                (?, ?, ?, ?)
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, loan.getBookId());
            stmt.setLong(2, loan.getUserId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4, Date.valueOf(loan.getReturnDate()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                loan.setId(rs.getLong(1));
                return loan;
            }

            return null;
        }
    }

}
