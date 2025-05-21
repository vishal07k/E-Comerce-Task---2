package com.ecomerce.ecomerce.repository;

import com.ecomerce.ecomerce.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should find category by category name")
    void testFindByCategoryName() {
        // Arrange
        Category category = new Category();
        category.setCategoryName("Electronics");
        categoryRepository.save(category);

        // Act
        Optional<Category> found = categoryRepository.findByCategoryName("Electronics");

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getCategoryName()).isEqualTo("Electronics");
    }

    @Test
    @DisplayName("Should return empty Optional when category does not exist")
    void testFindByCategoryName_NotFound() {
        // Act
        Optional<Category> found = categoryRepository.findByCategoryName("NonExistent");

        // Assert
        assertThat(found).isNotPresent();
    }
}
