package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.domain.Comment;
import com.techblog.blogtech.dto.CommentDto;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.AuthorRepository;
import com.techblog.blogtech.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService extends MainService<CommentDto, Long, Comment> {

    private final CommentRepository repository;
    private final AuthorRepository authorRepository;
    private final JwtService jwtService;

    public CommentService(JpaRepositoryImplementation<Comment, Long> repository, MainMapper<CommentDto, Comment> mapper, CommentRepository repository1, AuthorRepository authorRepository, JwtService jwtService) {
        super(repository, mapper);
        this.repository = repository1;
        this.authorRepository = authorRepository;
        this.jwtService = jwtService;
    }

    @Override
    public CommentDto create(CommentDto element) {
        element.setAuthor_id(extractAuthorFromPrincipal().getId());
        return super.create(element);
    }

    @Override
    public CommentDto updateById(Long id, CommentDto element) {
        Author author = extractAuthorFromPrincipal();
        if (!element.getAuthor_id().equals(author.getId())) {
            throw new IllegalArgumentException("Can not update the comment that does not own!");
        }
        return super.updateById(id, element);
    }

    @Override
    public void deleteById(Long id) {
        Author author = extractAuthorFromPrincipal();
        Comment comment = repository.findById(id).orElse(null);
        if (comment != null) {
            if (!comment.getAuthor().getId().equals(author.getId())) {
                throw new IllegalArgumentException("Can not delete the comment that does not own!");
            }
        }
        super.deleteById(id);
    }

    public Page<CommentDto> getMyAllComments(Pageable pageable) {
        Author author = extractAuthorFromPrincipal();
        Page<Comment> commentsByAuthorId = repository.findByAuthorIdOrderByCommentDateDesc(author.getId(), pageable);
        if (commentsByAuthorId.isEmpty())
            throw new NoDataFound("No comment found by author id: " + author.getId(), LocalDateTime.now());
        return commentsByAuthorId.map(getMapper()::toDto);
    }

    public Page<CommentDto> getCommentsByPostId(Long id, Pageable pageable) {
        Page<Comment> commentsByPostId = repository.findByPostIdOrderByCommentDateDesc(id, pageable);
        if (commentsByPostId.isEmpty())
            throw new NoDataFound("No comment found by post id: " + id, LocalDateTime.now());
        return commentsByPostId.map(getMapper()::toDto);
    }

    protected Author extractAuthorFromPrincipal() {
        String email = jwtService.extractUsername();
        return authorRepository.findByEmail(email).orElseThrow(() -> new NoDataFound("No user found!", LocalDateTime.now()));
    }
}
