package com.ecomerce.ecomerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.ecomerce.model.Category;

import io.swagger.v3.oas.annotations.Operation;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findByCategoryName(String categoryName);

}
