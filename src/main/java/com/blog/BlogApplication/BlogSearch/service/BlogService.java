package com.blog.BlogApplication.BlogSearch.service;

import com.blog.BlogApplication.BlogSearch.entity.Blog;
import com.blog.BlogApplication.BlogSearch.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> searchBlogs(String query) {
        return blogRepository.searchBlogs(query);
    }
    public List<Blog> searchBlogsByMultipleKeywords(String query) {
        String[] keywords = query.split(","); // Split by comma
        if (keywords.length >= 2) {
            return blogRepository.searchBlogsByMultipleKeywords(keywords[0].trim(), keywords[1].trim());
        } else {
            return blogRepository.searchBlogs(query); // If only one keyword, use default search
        }
    }
}
