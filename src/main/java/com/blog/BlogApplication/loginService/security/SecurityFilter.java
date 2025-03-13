package com.blog.BlogApplication.loginService.security;

import com.blog.BlogApplication.loginService.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = this.recoverToken(request); // Retrieve token from request
        if (token == null || token.isEmpty()) {
            System.out.println("No token found, proceeding without authentication.");
            filterChain.doFilter(request, response);
            return;
        }

        String subject = tokenService.validateToken(token); // Validate token and extract subject
        if (subject == null || subject.isEmpty()) {
            System.out.println("Invalid or expired token, proceeding without authentication.");
            filterChain.doFilter(request, response);
            return;
        }

        // Fetch user by email
        UserDetails user = userRepository.findByEmail(subject);

        if (user == null) {
            System.out.println("User not found for email: " + subject);
            filterChain.doFilter(request, response);
            return;
        }

        // Set authentication in security context
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("User authenticated: " + subject);
        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
