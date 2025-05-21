package com.ecomerce.ecomerce.repository;

import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("It should return all orders by given user")
    public void testFindByUser() {
        // Step 1: Create and save a user
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);

        //step 2: Create and save Products 
        Product product1 = new Product();
        product1.setName("Wireless Mouse");
        product1.setPrice(150.55);
        product1.setQty(150);
        
        Product product2 = new Product();
        product2.setName("Wireless KeyBoard");
        product2.setPrice(250.55);
        product2.setQty(250);
        
        Product saved1 = productRepository.save(product1);
        Product saved2 = productRepository.save(product2);
        
        
        // Step 3: Create and save orders for that user
        Order order1 = new Order();
        order1.setDateOfOrder(new Date());
        order1.setQty(2);
        List<Product> products = List.of(saved1, saved2);
        order1.setProduct(products);
        order1.setUser(savedUser);
        
        Order order2 = new Order();
        order2.setDateOfOrder(new Date());
        order2.setQty(5);
        order2.setProduct(products);
        order2.setUser(savedUser);

        orderRepository.save(order1);
        orderRepository.save(order2);

        // Step 4: Fetch orders using repository method
        List<Order> userOrders = orderRepository.findByUser(savedUser);

        // Step 4: Assert
        assertThat(userOrders).isNotEmpty();
        assertThat(userOrders.size()).isEqualTo(2);
        assertThat(userOrders.get(0).getId()).isEqualTo(savedUser.getId());
    }
}
