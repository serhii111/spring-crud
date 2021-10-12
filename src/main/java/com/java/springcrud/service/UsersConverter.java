package com.java.springcrud.service;

import com.java.springcrud.dto.UserDto;
import com.java.springcrud.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public Users fromUserDtoToUser(UserDto userDto) {
        Users users = new Users();
        users.setId(userDto.getId());
        users.setName(userDto.getName());
        users.setLogin(userDto.getLogin());
        users.setEmail(userDto.getEmail());
        return users;
    }

    public UserDto fromUserToUserDto(Users users) {
        return UserDto.builder()
                .id(users.getId())
                .name(users.getName())
                .login(users.getLogin())
                .email(users.getEmail())
                .build();
    }
}
