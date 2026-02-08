package com.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.entity.Feedback;
import com.feedback.repository.FeedbackRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {


@Autowired
private FeedbackRepository feedbackRepo;


@GetMapping("/dashboard")
public List<Feedback> viewDashboard() {
return feedbackRepo.findAll();
}
}
