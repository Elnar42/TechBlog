package com.techblog.blogtech.repository;

import com.techblog.blogtech.domain.Follower;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepositoryImplementation<Follower, Long> {
    List<Follower> findByFollowerId(Long followerId);

    List<Follower> findByFollowedId(Long followedId);

    Follower findByFollowerIdAndFollowedId(Long followerId, Long followedId);
}
