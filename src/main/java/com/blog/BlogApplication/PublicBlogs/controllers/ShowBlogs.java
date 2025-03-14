package com.blog.BlogApplication.PublicBlogs.controllers;

import java.util.List;
import java.util.Optional;

import com.blog.BlogApplication.loginService.security.TokenService;
import com.blog.BlogApplication.loginService.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.BlogApplication.loginService.services.GetData;
import com.blog.BlogApplication.PublicBlogs.Entity.Blog;
import com.blog.BlogApplication.PublicBlogs.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class ShowBlogs {

    @Autowired
    private BlogService blogService;

    @Autowired
    public GetData getData;

    @Autowired
    public UserService userService;

     // Create a new blog
    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpServletRequest request) {
        String email = getData.getEmail(request);
        blog.setEmail(email);
        String author  = userService.getUsernameByEmail(email);;
        blog.setAuthor(author);
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    // Get all blogs
    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    // Get blog by ID
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id, HttpServletRequest request) {
        String email = getData.getEmail(request);
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a blog
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Blog updatedBlog = blogService.updateBlog(id, blog);
        return updatedBlog != null ? ResponseEntity.ok(updatedBlog) : ResponseEntity.notFound().build();
    }

    // Delete a blog
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
