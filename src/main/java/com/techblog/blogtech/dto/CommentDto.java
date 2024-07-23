package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    @NotBlank
    @Length(max = 100)
    private String content;

    @Null
    private LocalDateTime commentDate;

    @NotNull
    private Long author_id;

    @NotNull
    private Long post_id;
}
