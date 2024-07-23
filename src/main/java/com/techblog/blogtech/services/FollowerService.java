package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Follower;
import com.techblog.blogtech.dto.FollowerDto;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.AuthorRepository;
import com.techblog.blogtech.repository.FollowerRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class FollowerService extends MainService<FollowerDto, Long, Follower> {

    private final FollowerRepository repository;
    private final AuthorRepository authorRepository;


    public FollowerService(JpaRepositoryImplementation<Follower, Long> repository, MainMapper<FollowerDto, Follower> mapper, FollowerRepository followerRepository, AuthorRepository authorRepository) {
        super(repository, mapper);
        this.repository = followerRepository;
        this.authorRepository = authorRepository;
    }

    public List<FollowerDto> findFollowersOfUserById(Long userId) {
        List<Follower> followers = repository.findByFollowedId(userId);
        return followers.stream()
                .map(getMapper()::toDto)
                .toList();
    }

    public List<FollowerDto> findUsersFollowedByUser(Long userId) {
        List<Follower> followed = repository.findByFollowerId(userId);
        return followed.stream()
                .map(getMapper()::toDto).toList();
    }

    public FollowerDto followUser(Long followerId, Long followedId) {
        if (followerId.equals(followedId)) throw new IllegalArgumentException("Can not follow yourself!");
        if (repository.findByFollowerIdAndFollowedId(followerId, followedId) != null)
            throw new IllegalArgumentException("Already followed!");
        Follower follower = new Follower();
        follower.setFollower(authorRepository.findById(followerId).orElseThrow(() -> new NoDataFound("No user found with id: " + followerId, LocalDateTime.now())));
        follower.setFollowed(authorRepository.findById(followedId).orElseThrow(() -> new NoDataFound("No user found with id: " + followedId, LocalDateTime.now())));
        return getMapper().toDto(repository.save(follower));
    }

    public void unfollowUser(Long followerId, Long followedId) {
        Follower follower = repository.findByFollowerIdAndFollowedId(followerId, followedId);
        if (follower == null) throw new IllegalArgumentException("Can not unfollow which doesn't follow!");
        repository.delete(follower);
    }

}
