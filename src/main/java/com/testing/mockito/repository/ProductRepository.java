package com.testing.mockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.mockito.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
