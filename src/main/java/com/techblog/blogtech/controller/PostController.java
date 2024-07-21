package com.techblog.blogtech.controller;

import com.techblog.blogtech.domain.Post;
import com.techblog.blogtech.dto.PostDto;
import com.techblog.blogtech.enums.Category;
import com.techblog.blogtech.services.MainService;
import com.techblog.blogtech.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController extends MainController<PostDto, Long, Post> {

    private final PostService postService;

    public PostController(MainService<PostDto, Long, Post> service, PostService postService) {
        super(service);
        this.postService = postService;
    }

    @GetMapping("/category/{category}")
    public Page<PostDto> getPostsByCategory(@PathVariable("category") Category category,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postService.findPostsByCategory(category, pageable);
    }
}
