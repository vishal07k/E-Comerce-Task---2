package com.ecomerce.ecomerce.controller;

import com.ecomerce.ecomerce.dtos.*;
import com.ecomerce.ecomerce.serviceImpl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Auth controller API's")
public class AuthController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private com.ecomerce.ecomerce.security.JwtUtil jwtUtil;

	@PostMapping("/login")
	@Operation(summary = "Login API")
	public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		System.out.println("Authenticated");
		String token = jwtUtil.generateToken(authRequest.getEmail());
		return ResponseEntity.ok(token);
	}

	@PostMapping("/register")
	@Operation(summary = "User Register API")
	public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
		UserDTO savedUser = null;
		try {
			savedUser = userService.createUser(userDto);
			return new ResponseEntity<>("User created Successfully !", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/home")
	    public ResponseEntity<String> home(){
	        return new ResponseEntity<>("Welcome to Ecomerce Management System", HttpStatus.OK);
	    }
	}
