package com.ecomerce.ecomerce.service;

import java.util.List;

import com.ecomerce.ecomerce.dtos.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO dto);
	
	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryById(Long categoryId);
	
}
