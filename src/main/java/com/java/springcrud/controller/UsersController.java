package com.java.springcrud.controller;

import com.java.springcrud.dto.UserDto;
import com.java.springcrud.exception.ValidationException;
import com.java.springcrud.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UsersController {

    private final UsersService usersService;

    @PostMapping(value = "save")
    public UserResponse saveUser(@RequestBody UserDto userDto) {
       try {
           log.info("Handling save user: " + userDto);
           UserResponse response = new UserResponse();
           response.setUserDto(usersService.saveUser(userDto));
           return response;
       } catch (ValidationException e) {
           log.info(e.getMessage());
           return new UserResponse(null, e.getMessage());
       }
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {
        log.info("Handling find by login" + login);
        return usersService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.info("Handling delete user request with Id: " + id);
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
