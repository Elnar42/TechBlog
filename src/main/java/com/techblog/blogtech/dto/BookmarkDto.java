package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookmarkDto {

    @Null
    private Long id;

    @NotNull
    private Long author_id;

    @NotNull
    private Long post_id;

    @Null
    private LocalDateTime savedDate;

}
