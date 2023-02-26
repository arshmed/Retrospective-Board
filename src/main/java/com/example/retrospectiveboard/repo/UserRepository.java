package com.example.retrospectiveboard.repo;

import com.example.retrospectiveboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}