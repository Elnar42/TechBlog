package com.techblog.blogtech.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends MainEntity {


    private String firstName;

    private String lastName;

    private String bibliography;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;


    @Column(nullable = false, updatable = false)
    private LocalDateTime joinDate;


    @PrePersist
    public void prePersist() {
        joinDate = LocalDateTime.now();
    }

}
