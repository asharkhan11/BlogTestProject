package com.blog.BlogApplication.BlogSearch.repository;

import com.blog.BlogApplication.BlogSearch.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(b.keywords) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Blog> searchBlogs(@Param("query") String query);
}

