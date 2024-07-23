package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.Post;
import com.techblog.blogtech.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper extends MainMapper<PostDto, Post> {

    @Mapping(target = "author_id", source = "author.id")
    PostDto toDto(Post entity);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author.id", source = "author_id")
    Post toEntity(PostDto dto);


}
