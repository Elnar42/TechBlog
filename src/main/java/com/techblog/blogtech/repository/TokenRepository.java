package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Token;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepositoryImplementation<Token, Long> {
    Optional<Token> findByToken(String token);
}
