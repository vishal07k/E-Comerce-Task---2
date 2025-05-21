//package com.ecomerce.ecomerce.serviceImpl;
//
//import com.ecomerce.ecomerce.dtos.*;
//import com.ecomerce.ecomerce.enums.Role;
//import com.ecomerce.ecomerce.model.User;
//
//import com.ecomerce.ecomerce.repository.UserRepository;
//import com.ecomerce.ecomerce.security.JwtService;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public AuthResponse register(RegisterRequest request) {
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);
//
//        userRepository.save(user);
//        String token = jwtService.generateToken(user.getEmail());
//        return new AuthResponse(token);
//    }
//
//    public AuthResponse login(LoginRequest request) {
//        Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        User user = userRepository.findByEmail(request.getEmail())
//            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        String token = jwtService.generateToken(user.getEmail());
//
//        return new AuthResponse(token);
//    }
//
//}
