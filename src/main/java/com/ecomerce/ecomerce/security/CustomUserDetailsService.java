package com.ecomerce.ecomerce.security;

import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User emp = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Provide role with prefix "ROLE_"
        return new org.springframework.security.core.userdetails.User(emp.getEmail(), emp.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(emp.getRole())));
    }
}
