package com.blog.BlogApplication.loginService.repositories;

import com.blog.BlogApplication.loginService.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

