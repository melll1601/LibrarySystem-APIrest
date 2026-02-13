package com.weg.LibrarySystem.controller;

import com.weg.LibrarySystem.model.User;
import com.weg.LibrarySystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
}
