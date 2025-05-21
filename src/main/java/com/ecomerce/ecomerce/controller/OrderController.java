	package com.ecomerce.ecomerce.controller;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

	import com.ecomerce.ecomerce.dtos.OrderDTO;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.UserRepository;
import com.ecomerce.ecomerce.serviceImpl.OrderServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
	//import com.ecomerce.ecomerce.service.OrderService;

	@RestController
	@RequestMapping("/orders")
	@Tag(name = "Orders", description = "Order Management API's")
	public class OrderController {

	    @Autowired
	    private OrderServiceImpl orderService;
	    
	    @Autowired
	    private UserRepository userRepository;

	    @PostMapping
	    @Operation(summary = "TO create Order")
	    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {
	        return ResponseEntity.ok(orderService.createOrder(dto));
	    }

//	    @PutMapping("/{id}")
//	    public ResponseEntity<OrderDTO> update(@PathVariable int id, @RequestBody OrderDTO dto) {
//	        return ResponseEntity.ok(orderService.updateOrder(id, dto));
//	    }
//
//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<Void> delete(@PathVariable int id) {
//	        orderService.deleteOrder(id);
//	        return ResponseEntity.noContent().build();
//	    }
//
//	    @GetMapping("/{id}")
//	    public ResponseEntity<OrderDTO> getById(@PathVariable int id) {
//	        return ResponseEntity.ok(orderService.getOrderById(id));
//	    }

	    @Operation(summary = "To Get All Order Records")
	    @GetMapping
	    public ResponseEntity<List<OrderDTO>> getAll() {
	        return ResponseEntity.ok(orderService.getAll());
	    }
	    
	    @Operation(summary = "TO get Orders By User")
	    @GetMapping("/user/{userId}")
	    public List<OrderDTO> getByUser(@PathVariable Long userId){
	    	
	    	return orderService.getOrdersByUser(userId);
	    }
	}