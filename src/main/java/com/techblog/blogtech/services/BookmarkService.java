package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Bookmark;
import com.techblog.blogtech.dto.BookmarkDto;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.BookmarkRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService extends MainService<BookmarkDto, Long, Bookmark> {

    private final BookmarkRepository repository;

    public BookmarkService(JpaRepositoryImplementation<Bookmark, Long> repository, MainMapper<BookmarkDto, Bookmark> mapper, BookmarkRepository bookmarkRepository) {
        super(repository, mapper);
        this.repository = bookmarkRepository;
    }

    public List<BookmarkDto> findByAuthorId(Long id, Pageable pageable) {
        return repository
                .findByAuthorId(id, pageable)
                .stream()
                .map(getMapper()::toDto)
                .toList();
    }
}
