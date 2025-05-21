package com.ecomerce.ecomerce.repository;

import com.ecomerce.ecomerce.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("It should find product by name")
    public void testFindByName() {
        // Step 1: Create and save a product
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1200.00);

        productRepository.save(product);

        // Step 2: Find product by name
        Optional<Product> optionalProduct = productRepository.findByName("Laptop");

        // Step 3: Assertions
        assertThat(optionalProduct).isPresent();
        assertThat(optionalProduct.get().getName()).isEqualTo("Laptop");
       
    }

    @Test
    @DisplayName("It should return empty when product name does not exist")
    public void testFindByNameNotFound() {
        Optional<Product> optionalProduct = productRepository.findByName("NonExistingProduct");
        assertThat(optionalProduct).isEmpty();
    }
}
