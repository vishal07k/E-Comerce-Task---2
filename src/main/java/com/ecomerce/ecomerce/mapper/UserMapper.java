package com.ecomerce.ecomerce.mapper;


import com.ecomerce.ecomerce.dtos.UserDTO;
import com.ecomerce.ecomerce.enums.Role;
import com.ecomerce.ecomerce.model.User;

public class UserMapper {
	 public static UserDTO toDto(User user) {
	        return new UserDTO(
	            user.getId(),
	            user.getName(),
	            user.getEmail(), 
	            user.getPassword(),
	            user.getRole()
	            
	        );
	    }

	    public static User toEntity(UserDTO dto) {
	        return new User(
	            dto.getId(),
	            dto.getName(),
	            dto.getEmail(),
	            dto.getPassword(),
	            dto.getRole(), null
	            
	        );
	    }
}