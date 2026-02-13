package com.weg.LibrarySystem.repository;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.util.ConnectionMysql;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean loanExists(Long id) throws SQLException{

        String query = """
                SELECT COUNT(*)
                FROM Loan
                WHERE id = ?    
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        return false;

    }

    public List<Loan> listLoan() throws SQLException{

        List<Loan> loans = new ArrayList<>();

        String query = """
                SELECT
                id, bookId, userId, loanDate, returnDate
                FROM Loan
                """;

        try(Connection conn = ConnectionMysql.connect();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                loans.add(new Loan(
                        rs.getLong("id"),
                        rs.getLong("bookId"),
                        rs.getLong("userId"),
                        rs.getDate("loanDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
            }
        }

        return loans;
    }

    public Loan searchByIdLoan(Long id) throws SQLException{


        String query = """
                SELECT
                id, bookId, userId, loanDate, returnDate
                FROM Loan
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                return (new Loan(
                        rs.getLong("id"),
                        rs.getLong("bookId"),
                        rs.getLong("userId"),
                        rs.getDate("loanDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
            }
        }

        return null;
    }

    public void updateLoan(Long id, Loan loan) throws SQLException{

        String query = """
                UPDATE Loan
                SET bookId = ?, userId = ?, loanDate = ?, returnDate = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, loan.getBookId());
            stmt.setLong(2, loan.getUserId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4, Date.valueOf(loan.getReturnDate()));
            stmt.setLong(5, id);

            stmt.executeUpdate();

        }
    }

    public boolean deleteLoan(Long id) throws SQLException{

        String query = """
                DELETE FROM Loan
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Nenhum contato encontrado");
                throw new RuntimeException();
            }

            return false;
        }
    }
}
