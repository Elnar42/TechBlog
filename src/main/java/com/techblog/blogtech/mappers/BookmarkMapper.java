package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.Bookmark;
import com.techblog.blogtech.dto.BookmarkDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookmarkMapper extends MainMapper<BookmarkDto, Bookmark> {

    @Mapping(target = "post_id", source = "post.id")
    @Mapping(target = "author_id", source = "author.id")
    BookmarkDto toDto(Bookmark entity);

    @Mapping(target = "post.id", source = "post_id")
    @Mapping(target = "author.id", source = "author_id")
    Bookmark toEntity(BookmarkDto dto);

}
