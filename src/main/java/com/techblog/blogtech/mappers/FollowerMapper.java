package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.Follower;
import com.techblog.blogtech.dto.FollowerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FollowerMapper extends MainMapper<FollowerDto, Follower> {

    @Mapping(target = "followed_id", source = "followed.id")
    @Mapping(target = "follower_id", source = "follower.id")
    FollowerDto toDto(Follower entity);

    @Mapping(target = "followed.id", source = "followed_id")
    @Mapping(target = "follower.id", source = "follower_id")
    Follower toEntity(FollowerDto dto);
}
