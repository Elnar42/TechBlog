package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.domain.Post;
import com.techblog.blogtech.dto.PostDto;
import com.techblog.blogtech.enums.Category;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.PostRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService extends MainService<PostDto, Long, Post> {

    private final PostRepository postRepository;
    private final AuthorService authorService;
    private final JwtService jwtService;


    public PostService(JpaRepositoryImplementation<Post, Long> repository, MainMapper<PostDto, Post> mapper, PostRepository postRepository, AuthorService authorService, JwtService jwtService) {
        super(repository, mapper);
        this.postRepository = postRepository;
        this.authorService = authorService;
        this.jwtService = jwtService;
    }

    public Page<PostDto> findPostsByCategory(Category category, Pageable pageable) {
        Page<Post> allByCategory = postRepository.findAllByCategoryOrderByPublishedDateDesc(category, pageable);
        if (allByCategory.isEmpty())
            throw new NoDataFound("No post found with a category: " + category.name(), LocalDateTime.now());
        return allByCategory.map(getMapper()::toDto);
    }

    @Override
    public PostDto create(@NotNull PostDto element) {
        Author author = extractAuthorFromPrincipal();
        element.setAuthor_id(author.getId());
        return getMapper().toDto(postRepository.save(getMapper().toEntity(element)));
    }

    @Override
    public PostDto updateById(Long id, PostDto element) {
        validateAuthor(id);
        return super.updateById(id, element);
    }

    @Override
    public void deleteById(Long id) {
        validateAuthor(id);
        super.deleteById(id);
    }

    protected Author extractAuthorFromPrincipal() {
        String email = jwtService.extractUsername();
        return authorService.loadUserByUserEmail(email);
    }

    protected void validateAuthor(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoDataFound::new);
        Author author = extractAuthorFromPrincipal();
        if (!post.getAuthor().getId().equals(author.getId()))
            throw new IllegalArgumentException("Can not update/delete post that does not belong to you!");
    }


}
