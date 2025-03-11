package com.blog.BlogApplication.loginService.controllers;

import com.blog.BlogApplication.loginService.users.dtos.AuthenticationDTO;
import com.blog.BlogApplication.loginService.users.dtos.LoginResponseDTO;
import com.blog.BlogApplication.loginService.users.dtos.RegisterDTO;
import com.blog.BlogApplication.loginService.users.User;
import com.blog.BlogApplication.loginService.security.TokenService;
import com.blog.BlogApplication.loginService.repositories.UserRepository;
import com.blog.BlogApplication.loginService.services.GmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = {"application/json"})
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates user login.
     *
     * @param data Object containing user credentials
     * @return ResponseEntity containing authentication token
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        String loginIdentifier = data.email(); // It could be email or username
        UserDetails user;

        // Determine if the input is an email or username

        if (GmailValidation.isValidGmail(loginIdentifier)) {
            // If it's an email, find user by email
            user = userRepository.findByEmail(loginIdentifier);
            if (user == null) {
                throw new UsernameNotFoundException("Email not found");
            }
        } else {
            // If it's a username, find user by username
            user = userRepository.findByUsername(loginIdentifier);
            if (user == null) {
                throw new UsernameNotFoundException("Username not found");
            }
        }
            // If it's not an email, treat it as a username
            var credentials = new UsernamePasswordAuthenticationToken(loginIdentifier, data.password());
            var auth = authenticationManager.authenticate(credentials);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    /**
     * Registers a new user.
     *
     * @param data Object containing user registration data
     * @return ResponseEntity indicating success or failure of registration
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if (this.userRepository.findByUsername(data.email())!= null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.email(), data.username(), encryptedPassword, data.role());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
