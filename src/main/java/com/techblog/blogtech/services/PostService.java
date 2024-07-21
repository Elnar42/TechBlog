package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Post;
import com.techblog.blogtech.dto.PostDto;
import com.techblog.blogtech.enums.Category;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.mappers.PostMapper;
import com.techblog.blogtech.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService extends MainService<PostDto, Long, Post> {

    private final PostRepository postRepository;
    private final PostMapper mapper;

    public PostService(JpaRepositoryImplementation<Post, Long> repository, MainMapper<PostDto, Post> mapper, PostRepository postRepository, PostMapper postMapper) {
        super(repository, mapper);
        this.postRepository = postRepository;
        this.mapper = postMapper;
    }


    public Page<PostDto> findPostsByCategory(Category category, Pageable pageable) {
        Page<Post> allByCategory = postRepository.findAllByCategoryOrderByPublishedDateDesc(category, pageable );
        if(allByCategory.isEmpty()) throw new NoDataFound("No post found with a category: " + category.name(), LocalDateTime.now());
        return allByCategory.map(mapper::toDto);
    }
}
