package com.ecomerce.ecomerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ecomerce.ecomerce.model.User;

@DataJpaTest
public class TestUserRepository {

	@Autowired
	private UserRepository userRepository;

	User user;

	@BeforeEach
	void setup() {

		user = new User();
		user.setName("John Cena");
		user.setEmail("john.cena@example.com");
		user.setPassword("Pass@123");

		// save user in in-memory database
		userRepository.save(user);

	}

	@Test
	@DisplayName("Test findById returns User when user is exists ")
	void TestFindById() {
		Optional<User> foundUser = userRepository.findById(user.getId());

		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getName()).isEqualTo("John Cena");

	}

	@Test
	@DisplayName("Test findByEmail returns empty when email does not exist")
	void testFindByEmailNotFound() {
		Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

		assertThat(foundUser).isNotPresent();
	}
}
