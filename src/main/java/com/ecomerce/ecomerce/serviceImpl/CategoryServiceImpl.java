package com.ecomerce.ecomerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dtos.CategoryDTO;
import com.ecomerce.ecomerce.mapper.CategoryMapper;
import com.ecomerce.ecomerce.model.Category;
import com.ecomerce.ecomerce.repository.CategoryRepository;
import com.ecomerce.ecomerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public CategoryDTO createCategory(CategoryDTO dto) {
		System.out.println(dto.getCategoryName());
		return CategoryMapper.toDTO(categoryRepository.save(CategoryMapper.toEntity(dto)));
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
	List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
	
	List<Category> categories = categoryRepository.findAll();
	for(Category category : categories) {
		dtos.add(CategoryMapper.toDTO(category));
	}
	
		return dtos;
	}

	@Override
	public CategoryDTO getCategoryById(Long categoryId) {
	
		return CategoryMapper.toDTO(categoryRepository.findById(categoryId).get());
	}

}
