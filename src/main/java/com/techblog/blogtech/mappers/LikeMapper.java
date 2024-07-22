package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.Like;
import com.techblog.blogtech.dto.LikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper extends MainMapper<LikeDto, Like> {

    @Mapping(target = "post_id", source = "post.id")
    @Mapping(target = "author_id", source = "author.id")
    LikeDto toDto(Like entity);

    @Mapping(target = "post.id", source = "post_id")
    @Mapping(target = "author.id", source = "author_id")
    Like toEntity(LikeDto dto);

}
