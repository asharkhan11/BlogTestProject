package com.blog.BlogApplication.BlogSearch.ser;

import com.blog.BlogApplication.BlogSearch.entity.Search;
import com.blog.BlogApplication.BlogSearch.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public List<Search> searchBlogs(String query) {
        List<Search> results = searchRepository.searchBlogs(query);

        // If no results from FTS, use fuzzy search
        if (results.isEmpty()) {
            results = searchRepository.searchBlogsFuzzy(query);
        }

        return results;
    }
    public List<Search> searchBlogsByMultipleKeywords(String query) {
        //String[] keywords = query.split(","); // Split by comma

            return searchRepository.searchBlogs(query); // If only one keyword, use default search
        }
    }

