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
        List<Blog> results = blogRepository.searchBlogs(query);

        // If no results from FTS, use fuzzy search
        if (results.isEmpty()) {
            results = blogRepository.searchBlogsFuzzy(query);
        }

        return results;
    }
    public List<Blog> searchBlogsByMultipleKeywords(String query) {
        //String[] keywords = query.split(","); // Split by comma

            return blogRepository.searchBlogs(query); // If only one keyword, use default search
        }
    }

