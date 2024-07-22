package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Like;
import com.techblog.blogtech.dto.LikeDto;
import com.techblog.blogtech.mappers.LikeMapper;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.LikeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService extends MainService<LikeDto, Long, Like> {

    private final LikeRepository likeRepository;
    private final LikeMapper mapper;

    public LikeService(JpaRepositoryImplementation<Like, Long> repository, MainMapper<LikeDto, Like> mapper, LikeRepository likeRepository, LikeMapper mapper1) {
        super(repository, mapper);
        this.likeRepository = likeRepository;
        this.mapper = mapper1;
    }

    public List<LikeDto> getLikesByUserId(Long userId, Pageable pageable) {
        return likeRepository
                .findByAuthorId(userId, pageable)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<LikeDto> getLikesByPostId(Long postId, Pageable pageable) {
        return likeRepository
                .findByPostId(postId, pageable)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
