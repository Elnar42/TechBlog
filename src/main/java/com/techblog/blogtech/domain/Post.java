package com.techblog.blogtech.domain;

import com.techblog.blogtech.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends MainEntity {

    @Lob
    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    private Category category;


    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Lob
    private String tags;


    @Column(nullable = false, updatable = false)
    private LocalDateTime publishedDate;


    @PrePersist
    public void prePersist() {
        publishedDate = LocalDateTime.now();

    }
}
