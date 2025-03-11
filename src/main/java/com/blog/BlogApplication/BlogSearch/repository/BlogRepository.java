package com.blog.BlogApplication.BlogSearch.repository;

import com.blog.BlogApplication.BlogSearch.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    // Primary: Use Full-Text Search (FTS)
    @Query(value = "SELECT * FROM blogs WHERE " +
            "to_tsvector('english', title || ' ' || content || ' ' || keywords || ' ' || category) @@ plainto_tsquery('english', :query) " +
            "ORDER BY ts_rank(to_tsvector('english', title || ' ' || content || ' ' || keywords || ' ' || category), plainto_tsquery('english', :query)) DESC",
            nativeQuery = true)
    List<Blog> searchBlogs(@Param("query") String query);

    // Fallback: Use Trigram similarity for fuzzy matching if no results from FTS
    @Query(value = "SELECT * FROM blogs WHERE " +
            "similarity(title, :query) > 0.3 OR " +
            "similarity(content, :query) > 0.3 OR " +
            "similarity(keywords, :query) > 0.3 OR " +
            "similarity(category, :query) > 0.3 " +
            "ORDER BY " +
            "greatest(similarity(title, :query), " +
            "similarity(content, :query), " +
            "similarity(keywords, :query), " +
            "similarity(category, :query)) DESC",
            nativeQuery = true)
    List<Blog> searchBlogsFuzzy(@Param("query") String query);
}