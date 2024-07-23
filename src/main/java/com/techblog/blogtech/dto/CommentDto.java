package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class CommentDto {

    @NotNull
    private Long post_id;

    @NotBlank
    @Length(max = 100)
    private String content;

    @Null
    private LocalDateTime commentDate;

    @Null
    private Long author_id;

}
