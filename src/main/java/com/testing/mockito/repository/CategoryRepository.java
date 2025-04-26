package com.testing.mockito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.mockito.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String categoryName);
	void deleteByName(String categoryName);
}
