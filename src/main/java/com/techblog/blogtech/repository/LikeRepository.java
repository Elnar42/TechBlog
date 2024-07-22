package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Like;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepositoryImplementation<Like, Long> {
    List<Like> findByAuthorId(Long userId, Pageable pageable);

    List<Like> findByPostId(Long postId, Pageable pageable);
}
