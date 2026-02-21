package com.weg.LibrarySystem.controller;

import com.weg.LibrarySystem.dto.user.UserRequestDto;
import com.weg.LibrarySystem.dto.user.UserResponseDto;
import com.weg.LibrarySystem.model.Book;
import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/librarySystem/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto postUser(
            @RequestBody UserRequestDto userRequestDto
    ){
        try{
            return  userService.registerUser(userRequestDto);
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    @GetMapping
    public List<UserResponseDto> getUsers(){

        try{
            return userService.listUsers();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public User searchByIdUser(@PathVariable Long id) {
        try {
            return userService.searchByIdUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user

    ) throws SQLException {
        user.setId(id);
        userService.updateUser(id, user);

        return user;
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) throws SQLException{
        return userService.deleteUser(id);
    }
}
