package com.weg.LibrarySystem.service;

import com.weg.LibrarySystem.dto.user.UserRequestDto;
import com.weg.LibrarySystem.dto.user.UserResponseDto;
import com.weg.LibrarySystem.mapper.UserMapper;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.Loan;
import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws SQLException {
        User user = userMapper.forEntity(userRequestDto);
        return userMapper.forResponseDto(userRepository.registerUser(user));
    }

    public List<UserResponseDto> listUsers() throws SQLException{
        List<User> users = userRepository.listUsers();
        return userMapper.forResponseListDto(users);
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

    public boolean deleteUser(Long id) throws SQLException {

        if (userRepository.userExists(id)){
            return userRepository.deleteUser(id);

        }else{
            throw new RuntimeException("ID does not exist");
        }
    }
}
