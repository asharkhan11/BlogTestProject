package com.blog.BlogApplication.loginService.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.blog.BlogApplication.loginService.security.TokenService;
import com.blog.BlogApplication.loginService.users.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetData {

    /*@Autowired
    private static TokenService tokenSer;*/

    @Value("${api.security.token.secret}")
    private String secret;

    public String getEmail(HttpServletRequest request) {
        String token = this.recoverToken(request);
        String subject = this.retriveEmail(token);
        //System.out.println("email is : "+ subject);
        return subject;
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    public String retriveEmail(String token) {

        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
    }

}
