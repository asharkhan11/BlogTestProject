package com.blog.BlogApplication.entities.users.dtos;

import com.blog.BlogApplication.entities.users.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
