package com.techblog.blogtech.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookmarks")
public class Bookmark extends MainEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(nullable = false, updatable = false)
    private LocalDateTime savedDate;


    @PrePersist
    public void prePersist() {
        savedDate = LocalDateTime.now();
    }

}
