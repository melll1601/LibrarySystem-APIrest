package com.weg.LibrarySystem.controller;

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
    public User postUser(
            @RequestBody User user
    ){
        try{
            user = userService.registerUser(user);
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }

        return user;
    }

    @GetMapping
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();

        try{
            users = userService.listUsers();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @GetMapping("/{id}")
    public User searchByIdUser(@PathVariable Long id) {
        try {
            return userService.searchByIdUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
