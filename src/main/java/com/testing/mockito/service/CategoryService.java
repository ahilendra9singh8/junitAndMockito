package com.testing.mockito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.mockito.entity.Category;
import com.testing.mockito.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository CategoryRepository;

	public Category createCategory(Category category) {
		Optional<Category> optionalCategory = CategoryRepository.findByName(category.getName());
		if (optionalCategory.isPresent()) {
			throw new IllegalStateException("Category already present" + category.getId());
		}
		Category savedCategory = CategoryRepository.save(category);
		return savedCategory;
	}

	public List<Category> getAllCategories() {
		List<Category> findAllCategory = CategoryRepository.findAll();
		return findAllCategory;
	}

	public Category getCategoryById(Long id) {
		Optional<Category> category = CategoryRepository.findById(id);
		return category.get();
	}

	public String deleteCategory(Long id) {
		CategoryRepository.deleteById(id);
		return "Category" + id + "has been deleted";
	}
}
