package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.domain.Bookmark;
import com.techblog.blogtech.dto.BookmarkDto;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.AuthorRepository;
import com.techblog.blogtech.repository.BookmarkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkService extends MainService<BookmarkDto, Long, Bookmark> {

    private final BookmarkRepository repository;
    private final AuthorRepository authorRepository;
    private final JwtService jwtService;


    public BookmarkService(JpaRepositoryImplementation<Bookmark, Long> repository, MainMapper<BookmarkDto, Bookmark> mapper, BookmarkRepository bookmarkRepository, AuthorRepository authorRepository, JwtService jwtService) {
        super(repository, mapper);
        this.repository = bookmarkRepository;
        this.authorRepository = authorRepository;
        this.jwtService = jwtService;
    }

    public List<BookmarkDto> getMySavedPosts(Pageable pageable) {
        return repository
                .findByAuthorId(extractAuthorFromPrincipal().getId(), pageable)
                .stream()
                .map(getMapper()::toDto)
                .toList();
    }

    @Override
    public BookmarkDto create(BookmarkDto element) {
        element.setAuthor_id(extractAuthorFromPrincipal().getId());
        return super.create(element);
    }

    @Override
    public Page<BookmarkDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public BookmarkDto getById(Long id) {
        return null;
    }

    @Override
    public BookmarkDto updateById(Long id, BookmarkDto element) {
        Author author = extractAuthorFromPrincipal();
        if (!element.getAuthor_id().equals(author.getId())) {
            throw new IllegalArgumentException("Can not update the bookmark that does not own!");
        }
        return super.updateById(id, element);
    }

    @Override
    public void deleteById(Long id) {
        Author author = extractAuthorFromPrincipal();
        Bookmark bookmark = repository.findById(id).orElse(null);
        if (bookmark != null) {
            if (!bookmark.getAuthor().getId().equals(author.getId())) {
                throw new IllegalArgumentException("Can not delete the bookmark that does not own!");
            }
        }
        super.deleteById(id);
    }

    protected Author extractAuthorFromPrincipal() {
        String email = jwtService.extractUsername();
        return authorRepository.findByEmail(email).orElseThrow(() -> new NoDataFound("No user found!", LocalDateTime.now()));
    }
}
