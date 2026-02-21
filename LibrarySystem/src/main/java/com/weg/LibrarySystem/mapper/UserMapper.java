package com.weg.LibrarySystem.mapper;

import com.weg.LibrarySystem.dto.user.UserRequestDto;
import com.weg.LibrarySystem.dto.user.UserResponseDto;
import com.weg.LibrarySystem.model.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public User forEntity(UserRequestDto userRequestDto){
        return new User(
                userRequestDto.name(),
                userRequestDto.email()
        );
    }

    public UserResponseDto forResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public List<UserResponseDto> forResponseListDto(List<User> users) throws SQLException{
        List<UserResponseDto> listResponseUser = new ArrayList<>();

        for (User user : users){
            listResponseUser.add(forResponseDto(user));
        }

        return listResponseUser;
    }
}
