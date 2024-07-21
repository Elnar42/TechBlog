package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowerDto {

    @Positive
    @NotNull
    private Long follower_id;

    @Positive
    @NotNull
    private Long followed_id;

}
