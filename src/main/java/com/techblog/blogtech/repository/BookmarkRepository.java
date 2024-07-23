package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepositoryImplementation<Bookmark, Long> {
    List<Bookmark> findByAuthorId(Long userId, Pageable pageable);
}
