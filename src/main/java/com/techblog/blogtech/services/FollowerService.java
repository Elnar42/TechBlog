package com.techblog.blogtech.services;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.domain.Follower;
import com.techblog.blogtech.dto.FollowerDto;
import com.techblog.blogtech.exceptions.NoDataFound;
import com.techblog.blogtech.mappers.MainMapper;
import com.techblog.blogtech.repository.AuthorRepository;
import com.techblog.blogtech.repository.FollowerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class FollowerService extends MainService<FollowerDto, Long, Follower> {

    private final FollowerRepository repository;
    private final AuthorRepository authorRepository;
    private final JwtService jwtService;


    public FollowerService(JpaRepositoryImplementation<Follower, Long> repository, MainMapper<FollowerDto, Follower> mapper, FollowerRepository followerRepository, AuthorRepository authorRepository, JwtService jwtService) {
        super(repository, mapper);
        this.repository = followerRepository;
        this.authorRepository = authorRepository;
        this.jwtService = jwtService;
    }

    public List<FollowerDto> findMyFollowers() {
        List<Follower> followers = repository.findByFollowedId(extractAuthorFromPrincipal().getId());
        return followers.stream()
                .map(getMapper()::toDto)
                .toList();
    }

    public List<FollowerDto> findUsersThatIFollow() {
        List<Follower> followed = repository.findByFollowerId(extractAuthorFromPrincipal().getId());
        return followed.stream()
                .map(getMapper()::toDto).toList();
    }

    public FollowerDto followUser(Long followedId) {
        Author author = extractAuthorFromPrincipal();
        if (author.getId().equals(followedId)) throw new IllegalArgumentException("Can not follow yourself!");
        if (repository.findByFollowerIdAndFollowedId(author.getId(), followedId) != null)
            throw new IllegalArgumentException("Already followed!");
        Follower follower = new Follower();
        follower.setFollower(authorRepository.findById(author.getId()).orElseThrow(() -> new NoDataFound("No user found with id: " + author.getId(), LocalDateTime.now())));
        follower.setFollowed(authorRepository.findById(followedId).orElseThrow(() -> new NoDataFound("No user found with id: " + followedId, LocalDateTime.now())));
        return getMapper().toDto(repository.save(follower));
    }

    public void unfollowUser(Long followedId) {
        Author author = extractAuthorFromPrincipal();
        Follower follower = repository.findByFollowerIdAndFollowedId(author.getId(), followedId);
        if (follower == null) throw new IllegalArgumentException("Can not unfollow which doesn't follow!");
        repository.delete(follower);
    }

    @Override
    public Page<FollowerDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public FollowerDto getById(Long aLong) {
        return null;
    }

    @Override
    public FollowerDto create(FollowerDto element) {
        Author author = extractAuthorFromPrincipal();
        element.setFollower_id(author.getId());
        return super.create(element);
    }

    @Override
    public FollowerDto updateById(Long id, FollowerDto element) {
        if (!element.getFollower_id().equals(extractAuthorFromPrincipal().getId())) {
            throw new IllegalArgumentException("Can not unfollow!");
        }
        return super.updateById(id, element);
    }

    @Override
    public void deleteById(Long id) {
        Author author = extractAuthorFromPrincipal();
        Follower follower = repository.findById(id).orElseThrow(() -> new NoDataFound("Have not liked!", LocalDateTime.now()));
        if (!follower.getFollower().getId().equals(author.getId()))
            throw new NoDataFound("Can not unfollow!", LocalDateTime.now());
        super.deleteById(id);
    }

    protected Author extractAuthorFromPrincipal() {
        String email = jwtService.extractUsername();
        return authorRepository.findByEmail(email)
                .orElseThrow(() -> new NoDataFound("No authenticated user found!", LocalDateTime.now()));
    }


}
