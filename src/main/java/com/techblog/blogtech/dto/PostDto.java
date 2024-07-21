package com.techblog.blogtech.dto;

import com.techblog.blogtech.enums.Category;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDto {

    private Long id;

    @NotBlank
    @Length(max = 50)
    private String title;

    @NotBlank
    @Lob
    private String content;

    @Positive
    @NotNull
    private Long author_id;

    @NotNull(message = "Category can not be null!")
    private Category category;

    @NotBlank
    private String tags;

    @Null
    private LocalDateTime publishedDate;

}
