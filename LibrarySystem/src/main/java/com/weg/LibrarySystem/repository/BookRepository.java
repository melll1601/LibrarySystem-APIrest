package com.weg.LibrarySystem.repository;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.util.ConnectionMysql;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class BookRepository {

    public Book registerBook(Book book) throws SQLException {

        String query = """
                INSERT INTO Book
                (title, author, yearPublication)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYearPublication());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                book.setId(rs.getLong(1));
                return book;
            }
        }

        return null;
    }

    public boolean bookExists(Long id) throws SQLException{

        String query = """
                SELECT COUNT(*)
                FROM Book
                WHERE id =?
                """;

        try (Connection conn = ConnectionMysql.connect();
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
}
