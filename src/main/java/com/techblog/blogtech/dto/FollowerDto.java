package com.techblog.blogtech.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class FollowerDto {

    @Null
    private Long id;

    @Null
    private Long follower_id;

    @Positive
    @NotNull
    private Long followed_id;


    @Null
    private LocalDateTime followStartedDate;

}
