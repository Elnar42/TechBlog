package com.techblog.blogtech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    private String content;

    private LocalDateTime commentDate;

    private Long author_id;

    private Long post_id;
}
