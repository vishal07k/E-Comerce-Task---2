package com.ecomerce.ecomerce.service;

import java.util.List;

import com.ecomerce.ecomerce.dtos.ProductDTO;

public interface ProductService {
	 ProductDTO createProduct(ProductDTO productDTO);
	    List<ProductDTO> getAllProducts();
	    ProductDTO getProductById(Long id);
	    ProductDTO updateProduct(long id, ProductDTO dto);
	    String deleteProduct(Long id);
}
