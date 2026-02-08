package com.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
List<Feedback> findByRating(int rating);
}
