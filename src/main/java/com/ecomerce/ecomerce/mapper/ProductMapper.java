package com.ecomerce.ecomerce.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecomerce.ecomerce.dtos.ProductDTO;
import com.ecomerce.ecomerce.model.Category;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.repository.CategoryRepository;



@Component
public class ProductMapper {

	@Autowired
	private CategoryRepository categoryRepository;
	
	 public  ProductDTO toDto(Product product) {
	        return new ProductDTO(
	            product.getId(),
	            product.getName(),
	            product.getPrice(),
	            product.getQty(),
	            product.getDescription(),
	            product.getCategory().stream().map(category ->{
	            	Long id = category.getId();
	            	return id;
	            	
	            }).collect(Collectors.toList())
	        );
	    }

	 public Product toEntity(ProductDTO dto) {
		    Product product = new Product();
		    product.setName(dto.getName());
		    product.setPrice(dto.getPrice());
		    product.setDescription(dto.getDescription());
		    product.setQty(dto.getQty());

		    if (dto.getCategoryId() != null && !dto.getCategoryId().isEmpty()) {
		        List<Category> categories = categoryRepository.findAllById(dto.getCategoryId());
		        product.setCategory(categories);
		    } else {
		        product.setCategory(Collections.emptyList()); // or throw custom exception if required
		    }

		    return product;
		}

	    
}