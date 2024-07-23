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

    @PostMapping("/follow/{id}")
    public FollowerDto followUser(@PathVariable Long id) {
        return followerService.followUser(id);
    }

    @DeleteMapping("/unfollow/{id}")
    public void unfollowUser(@PathVariable Long id) {
        followerService.unfollowUser(id);
    }

    @GetMapping("/mine")
    public List<FollowerDto> getFollowers() {
        return followerService.findMyFollowers();
    }

    @GetMapping("/followedByMe")
    public List<FollowerDto> getUserThatIFollow() {
        return followerService.findUsersThatIFollow();
    }
}
