package com.blog.BlogApplication.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;



    // Getters and Setters
}