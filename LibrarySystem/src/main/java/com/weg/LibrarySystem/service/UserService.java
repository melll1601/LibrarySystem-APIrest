package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(User user) throws SQLException {

        try {
            userRepository.registerUser(user);
            return user;

        }catch (SQLException error){
            throw new RuntimeException("Error registering user", error);
        }
    }
}
