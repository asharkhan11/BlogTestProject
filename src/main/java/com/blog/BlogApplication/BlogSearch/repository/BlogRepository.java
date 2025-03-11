package com.blog.BlogApplication.BlogSearch.repository;

import com.blog.BlogApplication.BlogSearch.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "SELECT * FROM blogs WHERE " +
            "to_tsvector('english', title || ' ' || content || ' ' || keywords || ' ' || category) @@ " +
            "plainto_tsquery('english', :query) " +
            "ORDER BY ts_rank(to_tsvector('english', title || ' ' || content || ' ' || keywords || ' ' || category), plainto_tsquery('english', :query)) DESC",
            nativeQuery = true)
    List<Blog> searchBlogs(@Param("query") String query);
}
