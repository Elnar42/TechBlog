package com.techblog.blogtech.mappers;

import com.techblog.blogtech.domain.Author;
import com.techblog.blogtech.dto.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends MainMapper<AuthorDto, Author> {

}
