package com.ecomerce.ecomerce.service;

import java.util.List;

import com.ecomerce.ecomerce.dtos.OrderDTO;

public interface OrderService {

	OrderDTO createOrder(OrderDTO dto);
    List<OrderDTO> getAll();
    List<OrderDTO> getOrdersByUser(Long userId);
}
