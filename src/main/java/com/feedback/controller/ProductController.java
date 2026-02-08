package com.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feedback.entity.Product;
import com.feedback.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Add product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
    	return productRepository.findByActiveTrue();
    }

    // Get product by id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
 // Update product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());

        return productRepository.save(existing);
    }

    // Delete product
    @DeleteMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id) {

        Product p = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setActive(false);
        productRepository.save(p);

        return "Product marked as inactive";
    }


}
