package com.ecomerce.ecomerce.service;

import java.util.List;

import com.ecomerce.ecomerce.dtos.UserDTO;

public interface UserService {
	 UserDTO createUser(UserDTO userDTO);
	    UserDTO getUserById(Long id);
	    List<UserDTO> getAllUsers();
	    UserDTO updateUser(long id, UserDTO userDTO);
	    String deleteUser(long id);
}
