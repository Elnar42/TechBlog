package com.techblog.blogtech.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techblog.blogtech.enums.Category;
import jakarta.persistence.Lob;
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
public class PostDto {

    @Null
    private Long id;

    @NotBlank
    @Length(max = 50)
    private String title;

    @NotBlank
    @Lob
    private String content;

    @NotNull(message = "Category can not be null!")
    private Category category;

    @NotBlank
    private String tags;

    @Null
    private Long author_id;

    @Null
    private LocalDateTime publishedDate;

}
