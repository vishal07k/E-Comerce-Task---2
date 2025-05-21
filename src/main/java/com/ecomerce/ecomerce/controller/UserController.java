package com.ecomerce.ecomerce.controller;

import com.ecomerce.ecomerce.dtos.UserDTO;
import com.ecomerce.ecomerce.serviceImpl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User-Service", description = "User API's")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

//    @PostMapping("/create")
//    public UserDTO createUser(@RequestBody UserDTO userDTO) {
//        return userService.createUser(userDTO);
//    }

    @Operation(summary = "To get/see specific user by Id")
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "To get/see all users")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "To update the User")
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Operation(summary = "To Delete the User")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
       String message = userService.deleteUser(id);
       return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
