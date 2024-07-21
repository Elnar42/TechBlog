package com.techblog.blogtech.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Nullable
    @Lob
    private String bibliography;

    @Null
    private LocalDateTime joinDate;

}
