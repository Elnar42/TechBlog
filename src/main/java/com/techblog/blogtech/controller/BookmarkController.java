package com.techblog.blogtech.controller;

import com.techblog.blogtech.domain.Bookmark;
import com.techblog.blogtech.dto.BookmarkDto;
import com.techblog.blogtech.services.BookmarkService;
import com.techblog.blogtech.services.MainService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController extends MainController<BookmarkDto, Long, Bookmark> {
    private final BookmarkService bookmarkService;

    public BookmarkController(MainService<BookmarkDto, Long, Bookmark> service, BookmarkService bookmarkService) {
        super(service);
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/mine")
    public List<BookmarkDto> getBookmarksByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return bookmarkService.getMySavedPosts(pageable);
    }
}