package com.blog.BlogApplication.loginService.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/add")


public class addition {


    @PostMapping("/sum")
    public int sum(){
        return 5+8;
    }
}
