package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class BookmarkDto {

    @Null
    private Long id;

    @Null
    private Long author_id;

    @NotNull
    private Long post_id;

    @Null
    private LocalDateTime savedDate;

}
