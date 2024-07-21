package com.techblog.blogtech.controller;

import com.techblog.blogtech.domain.Follower;
import com.techblog.blogtech.dto.FollowerDto;
import com.techblog.blogtech.services.FollowerService;
import com.techblog.blogtech.services.MainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/followers")
public class FollowerController extends MainController<FollowerDto, Long, Follower> {

    private final FollowerService followerService;

    public FollowerController(MainService<FollowerDto, Long, Follower> service, FollowerService followerService) {
        super(service);
        this.followerService = followerService;
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    public FollowerDto followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        return followerService.followUser(followerId, followedId);
    }

    @DeleteMapping("/{followerId}/unfollow/{followedId}")
    public void unfollowUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        followerService.unfollowUser(followerId, followedId);
    }

    @GetMapping("/follower/{userId}")
    public List<FollowerDto> getFollowers(@PathVariable Long userId) {
        return followerService.findFollowersOfUserById(userId);
    }

    @GetMapping("/followedBy/{userId}")
    public List<FollowerDto> getFollowedUsers(@PathVariable Long userId) {
        return followerService.findUsersFollowedByUser(userId);
    }
}
