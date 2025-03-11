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
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "ORDER BY " +
            "(CASE " +
            "WHEN LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) THEN 4 " +
            "WHEN LOWER(b.keywords) LIKE LOWER(CONCAT('%', :query, '%')) THEN 3 " +
            "WHEN LOWER(b.content) LIKE LOWER(CONCAT('%', :query, '%')) THEN 2 " +
            "WHEN LOWER(b.category) LIKE LOWER(CONCAT('%', :query, '%')) THEN 1 " +
            "ELSE 0 END) DESC")
    List<Blog> searchBlogs(@Param("query") String query);

    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "LOWER(b.keywords) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword2, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword2, '%')) OR " +
            "LOWER(b.keywords) LIKE LOWER(CONCAT('%', :keyword2, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :keyword2, '%')) " +
            "ORDER BY " +
            "(CASE " +
            "WHEN LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword2, '%')) THEN 4 " +
            "WHEN LOWER(b.keywords) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR LOWER(b.keywords) LIKE LOWER(CONCAT('%', :keyword2, '%')) THEN 3 " +
            "WHEN LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword2, '%')) THEN 2 " +
            "WHEN LOWER(b.category) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR LOWER(b.category) LIKE LOWER(CONCAT('%', :keyword2, '%')) THEN 1 " +
            "ELSE 0 END) DESC")
    List<Blog> searchBlogsByMultipleKeywords(@Param("keyword1") String keyword1,
                                             @Param("keyword2") String keyword2);
}

