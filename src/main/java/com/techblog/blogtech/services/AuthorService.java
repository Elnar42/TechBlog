package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.dto.AuthorDto;
import com.techblog.blogtech.mappers.MainMapper;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends MainService<AuthorDto, Long, Author> {

    public AuthorService(JpaRepositoryImplementation<Author, Long> repository, MainMapper<AuthorDto, Author> mapper) {
        super(repository, mapper);
    }
}
