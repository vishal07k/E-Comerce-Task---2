package com.ecomerce.ecomerce.serviceImpl;

import com.ecomerce.ecomerce.dtos.UserDTO;
import com.ecomerce.ecomerce.mapper.UserMapper;
import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldEncodePasswordAndSaveUser() {
        UserDTO userDTO = new UserDTO(1L, "Alice", "alice@example.com", "password", "USER");
        User user = new User(1L, "Alice", "alice@example.com", "encodedPwd", "USER", null);

        when(passwordEncoder.encode("password")).thenReturn("encodedPwd");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO saved = userService.createUser(userDTO);

        assertThat(saved.getName()).isEqualTo("Alice");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getUserById_shouldReturnUserDTO() {
        User user = new User(1L, "Bob", "bob@example.com", "pass", "ADMIN", null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        assertThat(result.getName()).isEqualTo("Bob");
    }

    @Test
    void getAllUsers_shouldReturnListOfUserDTOs() {
        List<User> users = List.of(
                new User(1L, "User1", "u1@example.com", "pwd", "USER", null),
                new User(2L, "User2", "u2@example.com", "pwd", "ADMIN", null)
        );

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.getAllUsers();

        assertThat(result).hasSize(2);
    }

    @Test
    void updateUser_shouldUpdateAndReturnDTO() {
        User existing = new User(1L, "Old", "old@example.com", "oldpwd", "USER", null);
        UserDTO updatedDTO = new UserDTO(1L, "New", "new@example.com", "newpwd", "ADMIN");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(passwordEncoder.encode("newpwd")).thenReturn("encodedPwd");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        UserDTO result = userService.updateUser(1L, updatedDTO);

        assertThat(result.getName()).isEqualTo("New");
        assertThat(result.getRole()).isEqualTo("ADMIN");
    }

    @Test
    void deleteUser_shouldDeleteAndReturnMessage() {
        String message = userService.deleteUser(5L);

        verify(userRepository).deleteById(5L);
        assertThat(message).isEqualTo("User deleted with id : 5");
    }

    @Test
    void getUserById_shouldThrowIfNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(99L);
        });
    }
}
