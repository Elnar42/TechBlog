package com.techblog.blogtech.controller;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.dto.AuthorDto;
import com.techblog.blogtech.services.MainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController extends MainController<AuthorDto, Long, Author> {
    public AuthorController(MainService<AuthorDto, Long, Author> service) {
        super(service);
    }
}
