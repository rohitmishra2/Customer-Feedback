package com.feedback.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
User findByEmail(String email);
}