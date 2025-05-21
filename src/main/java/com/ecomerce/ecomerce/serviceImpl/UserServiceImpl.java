package com.ecomerce.ecomerce.serviceImpl;

import com.ecomerce.ecomerce.dtos.UserDTO;
import com.ecomerce.ecomerce.mapper.UserMapper;
import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.UserRepository;
import com.ecomerce.ecomerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return UserMapper.toDto(userRepository.findById(id).get());
     }

   @Override
    public List<UserDTO> getAllUsers() {
    	List<UserDTO> dto = new ArrayList<UserDTO>();
    	List<User> users = userRepository.findAll();

    	for(User dt : users) {
    		dto.add(UserMapper.toDto(dt));
    	}
        return dto;
      }

    
    @Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
    	Optional<User> user = userRepository.findById(id);
    	if(user.isEmpty()) {
    		
    	}
    	
    	User newUser = user.get();
    	
    	newUser.setEmail(userDTO.getEmail());
    	newUser.setName(userDTO.getName());
    	newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    	newUser.setRole(userDTO.getRole());
    	
    	User savedUser = userRepository.save(newUser);
    	
        return UserMapper.toDto(savedUser);
    }

   
    @Override
    public String deleteUser(long id) {
    	
    	userRepository.deleteById(id);
    	return "User deleted with id : "+id;
    	
    }
}