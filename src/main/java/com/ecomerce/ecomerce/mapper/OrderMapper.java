package com.ecomerce.ecomerce.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecomerce.ecomerce.dtos.OrderDTO;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.repository.UserRepository;

@Component
public class OrderMapper {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderMapper(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public OrderDTO toDto(Order order) {
    	List<Long> productIds = new ArrayList<Long>();
    	
    	for(Product product : order.getProduct()) {
    		productIds.add(product.getId());
    	}
    	
        return new OrderDTO(
            order.getId(),
            order.getDateOfOrder(),
            order.getQty(),
            productIds,
            order.getUser().getId()
        );
    }

    public Order toEntity(OrderDTO dto) {
        List<Product> product = productRepository.findAllById(dto.getProductId());

        User user = userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found with ID " + dto.getUserId()));

        return new Order(
            dto.getId(),
            dto.getDateOfOrder(),
            dto.getQty(),
            user,
            product
        );
    }
}
