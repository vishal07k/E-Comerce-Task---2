package com.ecomerce.ecomerce.controller;

import com.ecomerce.ecomerce.dtos.AuthRequest;
import com.ecomerce.ecomerce.dtos.UserDTO;
import com.ecomerce.ecomerce.security.CustomUserDetailsService;
import com.ecomerce.ecomerce.security.JwtUtil;
import com.ecomerce.ecomerce.serviceImpl.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLoginSuccess() throws Exception {
        AuthRequest authRequest = new AuthRequest("user@example.com", "password");

        // Mock authentication success
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken("user@example.com", "password"));

        // Mock JWT token
        when(jwtUtil.generateToken("user@example.com")).thenReturn("fake-jwt-token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("fake-jwt-token"));
    }

    @Test
    void testLoginFailure() throws Exception {
        AuthRequest authRequest = new AuthRequest("user@example.com", "wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isUnauthorized()); // Update your controller to handle this explicitly
    }

    @Test
    void testRegisterSuccess() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Test");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("123456");

        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created Successfully !"));
    }

    @Test
    void testRegisterFailure() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Test");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("123456");

        when(userService.createUser(any(UserDTO.class)))
                .thenThrow(new RuntimeException("Email already exists"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }

    @Test
    void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/api/auth/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Ecomerce Management System"));
    }
}

