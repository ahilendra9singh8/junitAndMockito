package com.testing.mockito.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.testing.mockito.entity.Product;
import com.testing.mockito.service.ProductService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	void testCreateProduct() throws Exception {
		Product product = new Product(null, "Laptop", 1200.00);
		Product savedProduct = new Product(1L, "Laptop", 1200.00);

		when(productService.saveProduct(any(Product.class))).thenReturn(savedProduct);

		mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Laptop\",\"price\":1200.00}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("Laptop"));
	}

	@Test
	void testGetProduct_Found() throws Exception {
		Product product = new Product(1L, "Laptop", 1200.00);

		when(productService.getProductById(1L)).thenReturn(Optional.of(product));

		mockMvc.perform(get("/products/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Laptop"));
	}

	@Test
	void testGetProduct_NotFound() throws Exception {
		when(productService.getProductById(2L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/products/2")).andExpect(status().isNotFound());
	}

}
