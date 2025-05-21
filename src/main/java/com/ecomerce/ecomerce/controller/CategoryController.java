package com.ecomerce.ecomerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.ecomerce.dtos.CategoryDTO;
import com.ecomerce.ecomerce.serviceImpl.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/Category")
@Tag(name = "Category-Services", description = "Category API's")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	
	@PostMapping
	@Operation(summary = "TO create Category")
	public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
		System.out.println(categoryDTO.getCategoryName());
		return categoryService.createCategory(categoryDTO);
	}
	
	
	@GetMapping
	@Operation(summary = "TO get/See all the categories")
	public List<CategoryDTO> getAll(){
		return categoryService.getAllCategories();
	}
	

}
