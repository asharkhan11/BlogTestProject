package com.blog.BlogApplication.PublicBlogs.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "blogs_on_homepage")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    private String author;
    private String category;
    private String postDate;  // Storing as String
    private String authorProfile;
    private String blogImage;
}
