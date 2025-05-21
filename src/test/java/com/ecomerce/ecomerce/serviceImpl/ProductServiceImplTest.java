package com.ecomerce.ecomerce.serviceImpl;

import com.ecomerce.ecomerce.dtos.ProductDTO;
import com.ecomerce.ecomerce.mapper.ProductMapper;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_shouldSaveAndReturnDTO() {
        ProductDTO dto = new ProductDTO(1L, "Product A", 100.0, 10, null, null);
        Product entity = new Product(1L, "Product A", 100.0, 10, null, null, null);

        when(productRepository.save(any(Product.class))).thenReturn(entity);

        ProductDTO result = productService.createProduct(dto);

        assertThat(result.getName()).isEqualTo("Product A");
    }

    @Test
    void getAllProducts_shouldReturnProductDTOList() {
        Product product = new Product(1L, "Product A", 100.0, 10, null, null, null);
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> result = productService.getAllProducts();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Product A");
    }

    @Test
    void getProductById_shouldReturnProductDTO() {
        Product product = new Product(1L, "Product A", 100.0, 10, null, null, null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO result = productService.getProductById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void updateProduct_shouldUpdateAndReturnProductDTO() {
        Product existing = new Product(1L, "Old Name", 100.0, 5, null, null, null);
        ProductDTO updateDTO = new ProductDTO(1L, "New Name", 150.0, 10, null, null);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        ProductDTO result = productService.updateProduct(1L, updateDTO);

        assertThat(result.getName()).isEqualTo("New Name");
        assertThat(result.getPrice()).isEqualTo(150.0);
    }

    @Test
    void deleteProduct_shouldReturnSuccessMessage() {
        Long productId = 1L;

        String result = productService.deleteProduct(productId);

        verify(productRepository).deleteById(productId);
        assertThat(result).isEqualTo("Product deleted with Id : " + productId);
    }

    @Test
    void getProductById_shouldThrowException_ifNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.getProductById(99L));
    }
}
