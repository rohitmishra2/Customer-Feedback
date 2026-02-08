package com.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//fetch only active products
	List<Product> findByActiveTrue();
}
