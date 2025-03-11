package com.blog.BlogApplication.BlogSearch.controllers;

import com.blog.BlogApplication.BlogSearch.entity.Search;
import com.blog.BlogApplication.BlogSearch.ser.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<Search>> searchBlogs(@RequestParam("query") String query) {
       // List<Blog> blogs = blogService.searchBlogs(query);

            return ResponseEntity.ok(searchService.searchBlogs(query));

    }
}
