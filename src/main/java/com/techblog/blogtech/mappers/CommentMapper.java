package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.Comment;
import com.techblog.blogtech.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper extends MainMapper<CommentDto, Comment> {

    @Mapping(target = "author_id", source = "author.id")
    @Mapping(target = "post_id", source = "post.id")
    CommentDto toDto(Comment entity);

    @Mapping(target = "author.id", source = "author_id")
    @Mapping(target = "post.id", source = "post_id")
    Comment toEntity(CommentDto dto);

}
