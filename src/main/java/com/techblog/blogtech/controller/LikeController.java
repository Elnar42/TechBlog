package com.techblog.blogtech.controller;

import com.techblog.blogtech.domain.Like;
import com.techblog.blogtech.dto.LikeDto;
import com.techblog.blogtech.services.LikeService;
import com.techblog.blogtech.services.MainService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController extends MainController<LikeDto, Long, Like> {

    private final LikeService likeService;

    public LikeController(MainService<LikeDto, Long, Like> service, LikeService likeService) {
        super(service);
        this.likeService = likeService;
    }

    @GetMapping("/user/{id}")
    public List<LikeDto> getLikeByUser(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return likeService.getLikesByUserId(id, pageable);
    }

    @GetMapping("/post/{id}")
    public List<LikeDto> getLikeByPost(@PathVariable Long id,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return likeService.getLikesByPostId(id, pageable);
    }

}