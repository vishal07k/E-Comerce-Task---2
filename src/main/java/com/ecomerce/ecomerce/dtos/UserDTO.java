	package com.ecomerce.ecomerce.dtos;
	
	import java.util.List;

import com.ecomerce.ecomerce.enums.Role;
import com.ecomerce.ecomerce.model.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	 private Long id;
	    private String name;
	    private String email;
	    private String password;
	    private String role;
	   
}