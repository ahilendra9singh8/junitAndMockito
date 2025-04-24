package com.testing.mockito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.mockito.entity.Product;
import com.testing.mockito.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	NotificationService notificationService;

	public Product saveProduct(Product product) {
		Product saved = productRepository.save(product);
		notificationService.notifyProductCreated(saved.getName());
		return saved;
	}

	public Optional<Product> getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product;
	}

	public List<Product> getAllProduct() {
		List<Product> allProduct = productRepository.findAll();
		return allProduct;
	}

	public Product updateProduct(Product newProduct, Long id) {
		Product oldProduct = productRepository.findById(id).get();
		oldProduct.setName(newProduct.getName());
		oldProduct.setPrice(newProduct.getPrice());
		Product product = productRepository.save(oldProduct);
		return product;
	}

	public String deleteProduct(Long id) {
		String message = "";
		try {
			productRepository.deleteById(id);
			message = "delete Product successfully";
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}

}
