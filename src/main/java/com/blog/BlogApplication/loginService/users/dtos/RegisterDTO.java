package com.blog.BlogApplication.loginService.users.dtos;

import com.blog.BlogApplication.loginService.users.UserRole;

public record RegisterDTO(String name, String email, String username, String password, UserRole role) {
}
