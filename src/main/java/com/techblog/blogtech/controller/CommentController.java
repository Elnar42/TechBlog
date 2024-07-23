package com.techblog.blogtech.controller;

import com.techblog.blogtech.domain.Comment;
import com.techblog.blogtech.dto.CommentDto;
import com.techblog.blogtech.services.CommentService;
import com.techblog.blogtech.services.MainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController extends MainController<CommentDto, Long, Comment> {

    private final CommentService commentService;

    public CommentController(MainService<CommentDto, Long, Comment> service, CommentService commentService) {
        super(service);
        this.commentService = commentService;
    }

    @GetMapping("/mine")
    public Page<CommentDto> getCommentByAuthorId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commentService.getMyAllComments(pageable);
    }

    @GetMapping("/post/{id}")
    public Page<CommentDto> getCommentByPostId(@PathVariable Long id,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commentService.getCommentsByPostId(id, pageable);
    }
}
