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
public class LikeDto {

    @Null
    private Long id;

    @NotNull
    private Long post_id;

    @Null
    private Long author_id;

    @Null
    private LocalDateTime likeDate;

}
