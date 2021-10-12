package com.java.springcrud.service;

import com.java.springcrud.dto.UserDto;
import com.java.springcrud.exception.ValidationException;

import java.util.List;

public interface UsersService {

    UserDto saveUser(UserDto userDto) throws ValidationException;

    void deleteUser(Integer userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();
}
