package com.weg.LibrarySystem.repository;

import com.mysql.cj.log.Log;
import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.util.ConnectionMysql;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
}
