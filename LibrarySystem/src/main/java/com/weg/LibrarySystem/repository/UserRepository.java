package com.weg.LibrarySystem.repository;

import com.mysql.cj.log.Log;
import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.util.ConnectionMysql;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public User registerUser(User user) throws SQLException{

        String query = """
                INSERT INTO User
                (name, email)
                VALUES
                (?, ?)
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                user.setId(rs.getLong(1));
                return user;
            }

            return null;
        }
    }

    public boolean userExists(Long id) throws SQLException{

        String query = """
                SELECT COUNT(*)
                FROM User
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

    public List<User> listUsers() throws SQLException{

        List<User> users = new ArrayList<>();

        String query = """
                SELECT
                id, name, email
                FROM User
                """;

        try(Connection conn = ConnectionMysql.connect();
        PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        }

        return users;
    }

    public User searchByIdUser(Long id) throws SQLException{


        String query = """
                SELECT
                id, name, email
                FROM User
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return (new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        }

        return null;
    }

    public void updateUser(Long id, User user) throws SQLException{

        String query = """
                UPDATE User
                SET name = ?, email = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionMysql.connect();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setLong(3, id);

            stmt.executeUpdate();

        }
    }

    public boolean deleteUser(Long id) throws SQLException{

        String query = """
                DELETE FROM User
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
