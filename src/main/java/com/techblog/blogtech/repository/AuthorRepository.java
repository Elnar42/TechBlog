package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Author;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepositoryImplementation<Author, Long> {
    Optional<Author> findByEmail(String email);
}
