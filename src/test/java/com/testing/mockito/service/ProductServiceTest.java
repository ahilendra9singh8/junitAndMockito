package com.testing.mockito.service;

//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.testing.mockito.entity.Product;
import com.testing.mockito.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private NotificationService notificationService;

	@InjectMocks
	private ProductService productService;

	@Test
	void testSaveProduct() {
		Product product = new Product(null, "Phone", 999.99);
		Product savedProduct = new Product(1L, "Phone", 999.99);

		when(productRepository.save(product)).thenReturn(savedProduct);

		Product result = productService.saveProduct(product);

		Assertions.assertNotNull(result.getId());
		Assertions.assertEquals("Phone", result.getName());
		verify(notificationService).notifyProductCreated("Phone");
	}

	@Test
	void testGetProductById_Found() {
		Product product = new Product(1L, "Phone", 999.99);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Optional<Product> result = productService.getProductById(1L);

		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals("Phone", result.get().getName());
	}

	@Test
	void testGetProductById_NotFound() {
		when(productRepository.findById(2L)).thenReturn(Optional.empty());

		Optional<Product> result = productService.getProductById(2L);

		Assertions.assertFalse(result.isPresent());
	}
}

//package com.example.productapp.controller;
//
//import com.example.productapp.entity.Product;
//import com.example.productapp.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductService productService;
//
//    @Test
//    void testCreateProduct() throws Exception {
//        Product product = new Product(null, "Laptop", 1200.00);
//        Product savedProduct = new Product(1L, "Laptop", 1200.00);
//
//        when(productService.saveProduct(any(Product.class))).thenReturn(savedProduct);
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\":\"Laptop\",\"price\":1200.00}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Laptop"));
//    }
//
//    @Test
//    void testGetProduct_Found() throws Exception {
//        Product product = new Product(1L, "Laptop", 1200.00);
//
//        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
//
//        mockMvc.perform(get("/products/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Laptop"));
//    }
//
//    @Test
//    void testGetProduct_NotFound() throws Exception {
//        when(productService.getProductById(2L)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/products/2"))
//                .andExpect(status().isNotFound());
//    }
//}
