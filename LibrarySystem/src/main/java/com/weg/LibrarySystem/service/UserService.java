package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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

    public List<User> listUsers() throws SQLException{
        return userRepository.listUsers();
    }

    public User searchByIdUser(Long id) throws SQLException{

        if (userRepository.userExists(id)){
            return userRepository.searchByIdUser(id);
        }else {
            throw new RuntimeException("ID does not exist");
        }
    }
    public void updateUser(Long id, User user) throws SQLException{

        if (userRepository.userExists(id)){
            user.setId(id);
            userRepository.updateUser(id, user);

        }else {
            throw new RuntimeException("ID does not exist");
        }


    }
}
