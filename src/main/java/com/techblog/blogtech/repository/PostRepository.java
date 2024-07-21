package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Post;
import com.techblog.blogtech.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepositoryImplementation<Post, Long> {
    Page<Post> findAllByCategoryOrderByPublishedDateDesc(Category category, Pageable pageable);
}
