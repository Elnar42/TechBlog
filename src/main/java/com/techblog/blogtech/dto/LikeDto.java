package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeDto {

    private Long id;

    @NotNull
    private Long author_id;

    @NotNull
    private Long post_id;

}
