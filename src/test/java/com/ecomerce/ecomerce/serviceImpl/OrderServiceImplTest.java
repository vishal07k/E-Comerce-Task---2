package com.ecomerce.ecomerce.serviceImpl;

import com.ecomerce.ecomerce.dtos.OrderDTO;
import com.ecomerce.ecomerce.mapper.OrderMapper;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.model.User;
import com.ecomerce.ecomerce.repository.OrderRepository;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderDTO orderDTO;
    private Product product;
    private User user;
    private Order order;

    @BeforeEach
    void setUp() {
        orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setQty(2);
        orderDTO.setProductId(Arrays.asList(100L));

        product = new Product();
        product.setId(100L);
        product.setQty(10);

        user = new User();
        user.setId(1L);

        order = new Order();
    }

    @Test
    void testCreateOrder_Success() {
        when(productRepository.findAllById(orderDTO.getProductId())).thenReturn(Collections.singletonList(product));
        when(userRepository.findById(orderDTO.getUserId())).thenReturn(Optional.of(user));
        when(orderMapper.toEntity(orderDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDTO);

        OrderDTO createdOrder = orderService.createOrder(orderDTO);

        assertNotNull(createdOrder);
        verify(productRepository, times(1)).save(any(Product.class));
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testCreateOrder_InsufficientStock() {
        product.setQty(1); // less than order qty

        when(productRepository.findAllById(orderDTO.getProductId())).thenReturn(Collections.singletonList(product));
        when(userRepository.findById(orderDTO.getUserId())).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(orderDTO);
        });

        assertEquals("Insufficient product quantity in stock.", exception.getMessage());
        verify(productRepository, never()).save(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testCreateOrder_UserNotFound() {
        when(productRepository.findAllById(orderDTO.getProductId())).thenReturn(Collections.singletonList(product));
        when(userRepository.findById(orderDTO.getUserId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(orderDTO);
        });

        assertEquals("User not found with id: " + orderDTO.getUserId(), exception.getMessage());
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));
        when(orderMapper.toDto(order)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getAll();

        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrdersByUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(orderRepository.findByUser(user)).thenReturn(Collections.singletonList(order));
        when(orderMapper.toDto(order)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrdersByUser(1L);

        assertEquals(1, result.size());
        verify(orderRepository).findByUser(user);
    }

    @Test
    void testGetOrdersByUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.getOrdersByUser(1L);
        });

        assertEquals("User not Found !", exception.getMessage());
    }
}
