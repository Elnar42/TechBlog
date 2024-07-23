package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Comment;
import com.techblog.blogtech.dto.CommentDto;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService extends MainService<CommentDto, Long, Comment> {

    private final CommentRepository repository;

    public CommentService(JpaRepositoryImplementation<Comment, Long> repository, MainMapper<CommentDto, Comment> mapper, CommentRepository repository1) {
        super(repository, mapper);
        this.repository = repository1;
    }

    public Page<CommentDto> getCommentsByAuthorId(Long id, Pageable pageable) {
        Page<Comment> commentsByAuthorId = repository.findByAuthorIdOrderByCommentDateDesc(id, pageable);
        if (commentsByAuthorId.isEmpty())
            throw new NoDataFound("No comment found by author id: " + id, LocalDateTime.now());
        return commentsByAuthorId.map(getMapper()::toDto);
    }

    public Page<CommentDto> getCommentsByPostId(Long id, Pageable pageable) {
        Page<Comment> commentsByPostId = repository.findByPostIdOrderByCommentDateDesc(id, pageable);
        if (commentsByPostId.isEmpty())
            throw new NoDataFound("No comment found by post id: " + id, LocalDateTime.now());
        return commentsByPostId.map(getMapper()::toDto);
    }
}
