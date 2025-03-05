package com.blog.BlogApplication.controllers;

import com.blog.BlogApplication.entities.users.dtos.AuthenticationDTO;
import com.blog.BlogApplication.entities.users.dtos.LoginResponseDTO;
import com.blog.BlogApplication.entities.users.dtos.RegisterDTO;
import com.blog.BlogApplication.entities.users.User;
import com.blog.BlogApplication.infra.security.TokenService;
import com.blog.BlogApplication.repositories.UserRepository;
import com.blog.BlogApplication.services.GmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

        // Determine if the input is an email or username
        if (GmailValidation.isValidGmail(loginIdentifier)) {
            // If it's an email, authenticate with email
            var credentials = new UsernamePasswordAuthenticationToken(loginIdentifier, data.password());
            var auth = authenticationManager.authenticate(credentials);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            // If it's not an email, treat it as a username
            var credentials = new UsernamePasswordAuthenticationToken(loginIdentifier, data.password());
            var auth = authenticationManager.authenticate(credentials);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
    }

    /**
     * Registers a new user.
     *
     * @param data Object containing user registration data
     * @return ResponseEntity indicating success or failure of registration
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), data.email(), data.username(), encryptedPassword, data.role());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
