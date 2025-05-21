package com.ecomerce.ecomerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dtos.OrderDTO;
import com.ecomerce.ecomerce.mapper.OrderMapper;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.OrderRepository;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.repository.UserRepository;
import com.ecomerce.ecomerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        // Check and fetch Product
        List<Product> product1 = productRepository.findAllById(dto.getProductId());
                
        // Check and fetch User
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        // Update product stock
        for(Product product : product1){
        if (product.getQty() < dto.getQty()) {
            throw new RuntimeException("Insufficient product quantity in stock.");
        }
        product.setQty(product.getQty() - dto.getQty());
        productRepository.save(product);
        }
        // Create order entity
        Order order = orderMapper.toEntity(dto);

        // Save order
        order = orderRepository.save(order);

        // Return DTO
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> dtos = new ArrayList<>();
        for (Order order : orders) {
            dtos.add(orderMapper.toDto(order));
        }
        return dtos;
    }
    
    @Override
    public List<OrderDTO> getOrdersByUser(Long userId){
    	User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not Found !"));
    	
     List<Order> orders = orderRepository.findByUser(user);
     
     List<OrderDTO> dtos = new ArrayList<OrderDTO>();
     
     for(Order order : orders) {
    	 dtos.add(orderMapper.toDto(order));
     }
        	
    	return dtos;
    }
}
