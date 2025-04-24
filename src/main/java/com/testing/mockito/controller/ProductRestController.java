package com.testing.mockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.mockito.entity.Product;
import com.testing.mockito.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@PostMapping("/createProduct")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product getProduct = productService.saveProduct(product);
		return ResponseEntity.ok(getProduct);
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id).get();
		return ResponseEntity.ok(product);
	}  

}
