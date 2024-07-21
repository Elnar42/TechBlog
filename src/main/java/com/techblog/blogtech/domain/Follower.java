package com.techblog.blogtech.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

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

}
