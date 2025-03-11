package com.blog.BlogApplication.PublicBlogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.BlogApplication.PublicBlogs.Entity.Blog;
import com.blog.BlogApplication.PublicBlogs.repo.BlogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;


      // Create a new blog
      public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }
    
     // Get all blogs
     public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // Get a blog by ID
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    // Update a blog
    public Blog updateBlog(Long id, Blog updatedBlog) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(updatedBlog.getTitle());
            blog.setSubtitle(updatedBlog.getSubtitle());
            blog.setAuthor(updatedBlog.getAuthor());
            blog.setCategory(updatedBlog.getCategory());
            blog.setPostDate(updatedBlog.getPostDate());
            blog.setAuthorProfile(updatedBlog.getAuthorProfile());
            blog.setBlogImage(updatedBlog.getBlogImage());
            return blogRepository.save(blog);
        }).orElse(null);
    }

    // Delete a blog by ID
    public boolean deleteBlog(Long id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
