package com.feedback.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feedback.entity.Feedback;
import com.feedback.entity.Product;
import com.feedback.entity.User;
import com.feedback.repository.FeedbackRepository;
import com.feedback.repository.ProductRepository;
import com.feedback.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public Feedback addFeedback(@Valid @RequestBody Feedback feedback, Principal principal) {

        if (principal == null) throw new RuntimeException("User not authenticated");

        User user = userRepository.findByEmail(principal.getName());
        if (user == null) throw new RuntimeException("User not found from token");

        feedback.setUser(user);

        // ✅ Handle product if provided
        if (feedback.getProduct() != null && feedback.getProduct().getProductId() != null) {
            Product product = productRepository.findById(feedback.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            feedback.setProduct(product);
        }

        return feedbackRepo.save(feedback);
    }

    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    @GetMapping("/rating/{rating}")
    public List<Feedback> getByRating(@PathVariable int rating) {
        return feedbackRepo.findByRating(rating);
    }
}
