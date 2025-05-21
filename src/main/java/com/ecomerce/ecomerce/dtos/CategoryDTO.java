package com.ecomerce.ecomerce.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryDTO {

	private Long id;
    private String categoryName;
    
	
}
