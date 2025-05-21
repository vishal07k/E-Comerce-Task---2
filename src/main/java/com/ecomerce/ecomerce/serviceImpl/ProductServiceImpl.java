package com.ecomerce.ecomerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dtos.ProductDTO;
import com.ecomerce.ecomerce.mapper.ProductMapper;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductMapper productMapper;
	
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = productRepository.save(productMapper.toEntity(productDTO));
		return productMapper.toDto(product);
	}
	
	public List<ProductDTO> getAllProducts(){
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(Product product : products) {
			productDTOs.add(productMapper.toDto(product));
		}
		
		return productDTOs;
	}
	
	public ProductDTO getProductById(Long id) {
		Product product = productRepository.findById(id).get();
		return productMapper.toDto(product);
	}
	
	public ProductDTO updateProduct(long id, ProductDTO dto) {
		
		Optional<Product> product = productRepository.findById(id);
		
		Product preProduct = product.get();
		preProduct.setName(dto.getName());
		preProduct.setPrice(dto.getPrice());
		preProduct.setQty(dto.getQty());
		
		return productMapper.toDto(productRepository.save(preProduct));
		
	}
	
	public String deleteProduct(Long id) {
		productRepository.deleteById(id);
		return "Product deleted with Id : " + id;
	}
}
