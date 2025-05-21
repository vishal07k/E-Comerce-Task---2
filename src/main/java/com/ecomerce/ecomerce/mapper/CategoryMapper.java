package com.ecomerce.ecomerce.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ecomerce.ecomerce.dtos.CategoryDTO;
import com.ecomerce.ecomerce.model.Category;
import com.ecomerce.ecomerce.model.Product;

public class CategoryMapper {

	 public static CategoryDTO toDTO(Category category) {
	        return CategoryDTO.builder()
	                .id(category.getId())
	                .categoryName(category.getCategoryName())
	                .build();
	    }

	    public static Category toEntity(CategoryDTO dto) {
	        return Category.builder()
	                .id(dto.getId())
	                .categoryName(dto.getCategoryName())
	                .build();
	    }}
