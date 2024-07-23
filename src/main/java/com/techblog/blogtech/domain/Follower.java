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
@Table(name = "followers")
public class Follower extends MainEntity {

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private Author follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "id")
    private Author followed;

    @Column(nullable = false, updatable = false)
    private LocalDateTime followStartedDate;


    @PrePersist
    public void prePersist() {
        followStartedDate = LocalDateTime.now();
    }

}
