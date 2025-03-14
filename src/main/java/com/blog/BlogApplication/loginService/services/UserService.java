package com.blog.BlogApplication.loginService.services;

import com.blog.BlogApplication.loginService.repositories.NewRepo;
import com.blog.BlogApplication.loginService.users.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final NewRepo userRepository;

    public UserService(NewRepo userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsernameByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(User::getUsername).orElse(null);
    }
}
