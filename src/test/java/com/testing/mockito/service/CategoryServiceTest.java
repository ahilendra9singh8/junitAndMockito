package com.testing.mockito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testing.mockito.entity.Category;
import com.testing.mockito.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryService categoryService;

	private Category category;

	@BeforeEach
	void setUp() {
		category = new Category();
		category.setId(1L);
		category.setName("Test");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testCreateCategoty_categoryShouldbeCreated() {
		when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
		when(categoryRepository.save(category)).thenReturn(category);
		Category savedCategory = categoryService.createCategory(category);
		assertNotNull(savedCategory);
		assertEquals(category.getName(), savedCategory.getName());
	}

	@Test
	void testCreateCategoty_ShouldThrowException_WhenCategoryAlreadyExists() {
		when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
		assertThrows(IllegalStateException.class, () -> categoryService.createCategory(category));
		verify(categoryRepository, times(0)).save(category);
	}

//	@Test
//	void testGetAllCategories() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetCategoryById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteCategory() {
//		fail("Not yet implemented");
//	}

}
