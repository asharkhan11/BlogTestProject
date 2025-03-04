package com.blog.BlogApplication.users.dtos;

import com.blog.BlogApplication.users.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
