package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.domain.Like;
import com.techblog.blogtech.dto.LikeDto;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.AuthorRepository;
import com.techblog.blogtech.repository.LikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeService extends MainService<LikeDto, Long, Like> {

    private final LikeRepository likeRepository;
    private final AuthorRepository authorRepository;
    private final JwtService jwtService;


    public LikeService(JpaRepositoryImplementation<Like, Long> repository, MainMapper<LikeDto, Like> mapper, LikeRepository likeRepository, AuthorRepository authorRepository, JwtService jwtService) {
        super(repository, mapper);
        this.likeRepository = likeRepository;
        this.authorRepository = authorRepository;
        this.jwtService = jwtService;
    }

    public List<LikeDto> getPostsThatILike(Pageable pageable) {
        String email = jwtService.extractUsername();
        Author author = authorRepository.findByEmail(email).orElseThrow(() -> new NoDataFound("No user found!", LocalDateTime.now()));
        return likeRepository
                .findByAuthorId(author.getId(), pageable)
                .stream()
                .map(getMapper()::toDto)
                .toList();
    }

    @Override
    public Page<LikeDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public LikeDto getById(Long id) {
        return null;
    }

    @Override
    public LikeDto create(LikeDto element) {
        Author author = extractAuthorFromPrincipal();
        element.setAuthor_id(author.getId());
        return super.create(element);
    }

    @Override
    public LikeDto updateById(Long id, LikeDto element) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Author author = extractAuthorFromPrincipal();
        Like like = likeRepository.findById(id).orElseThrow(() -> new NoDataFound("Have not liked!", LocalDateTime.now()));
        if (!like.getAuthor().getId().equals(author.getId()))
            throw new NoDataFound("Can not unlike the post that you did not like!", LocalDateTime.now());
        super.deleteById(id);
    }

    protected Author extractAuthorFromPrincipal() {
        String email = jwtService.extractUsername();
        return authorRepository.findByEmail(email).orElseThrow(() -> new NoDataFound("No user found!", LocalDateTime.now()));
    }


}
