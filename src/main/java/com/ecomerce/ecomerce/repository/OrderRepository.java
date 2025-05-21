package com.ecomerce.ecomerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.ecomerce.ecomerce.dtos.OrderDTO;
import com.ecomerce.ecomerce.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	 List<Order> findByUser(com.ecomerce.ecomerce.model.User user);
}
