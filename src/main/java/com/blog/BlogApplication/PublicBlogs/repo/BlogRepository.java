package com.blog.BlogApplication.PublicBlogs.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.BlogApplication.PublicBlogs.Entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}

