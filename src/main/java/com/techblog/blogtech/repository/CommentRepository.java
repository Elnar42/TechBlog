package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepositoryImplementation<Comment, Long> {

    Page<Comment> findByAuthorIdOrderByCommentDateDesc(Long authorId, Pageable pageable);

    Page<Comment> findByPostIdOrderByCommentDateDesc(Long blogId, Pageable pageable);
}
