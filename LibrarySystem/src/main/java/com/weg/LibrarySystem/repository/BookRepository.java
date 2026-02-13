package com.weg.LibrarySystem.repository;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.util.ConnectionMysql;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    public List<Book> listBooks() throws SQLException{

        List<Book> books = new ArrayList<>();

        String query = """
                SELECT
                id, title, author, yearPublication
                FROM Book
                """;

        try(Connection conn = ConnectionMysql.connect();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                books.add(new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("yearPublication")
                ));
            }
        }
        return books;
    }

    public Book searchByIdBook(Long id) throws SQLException {

        String query = """
                SELECT
                id, title, author, yearPublication
                FROM Book
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("yearPublication")
                );
            }
        }

        return null;
    }

    public void updateBook(Long id, Book book) throws SQLException{

        String query = """
                UPDATE Book SET title = ?, author = ?, yearPublication = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYearPublication());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        }
    }
}
