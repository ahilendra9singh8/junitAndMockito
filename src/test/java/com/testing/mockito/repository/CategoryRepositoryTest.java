package com.testing.mockito.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testing.mockito.entity.Category;

@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	private Category category;

	@BeforeEach
	void setUp() {
//		Insert row in category table
		category = new Category();
		category.setName("Test");
		categoryRepository.save(category);
	}

	@AfterEach
	void tearDown() {
//		Delete row from category table
		categoryRepository.delete(category);
	}

	@Test
	void testFindByName() {
		Category foundCategory = categoryRepository.findByName("Test").orElse(null);
		assertNotNull(foundCategory);
		assertEquals(category.getName(), foundCategory.getName());
	}

	@Test
	void testDeleteByName() {
		categoryRepository.deleteByName("Test");
		Category foundCategory = categoryRepository.findByName("Test").orElse(null);
		assertNull(foundCategory);
	}

}
