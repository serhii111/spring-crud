package com.java.springcrud.service;

import com.java.springcrud.dto.UserDto;
import com.java.springcrud.entity.Users;
import com.java.springcrud.exception.ValidationException;
import com.java.springcrud.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;

    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto);
        Users savedUser = usersRepository.save(usersConverter.fromUserDtoToUser(userDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }

    private void validateUserDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(userDto.getLogin()) || userDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
        String loginToValidate = userDto.getLogin();
        if (findByLogin(loginToValidate) != null) {
            throw new ValidationException("Login already exists");
        }
    }

    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    public UserDto findByLogin(String login) {
        Users users = usersRepository.findByLogin(login);
        if (users != null) {
            return usersConverter.fromUserToUserDto(users);
        }
        return null;
    }

    public List<UserDto> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}
