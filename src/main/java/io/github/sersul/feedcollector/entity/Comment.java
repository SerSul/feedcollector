package io.github.sersul.feedcollector.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(nullable = true, name = "user_name")
    private String userName = "Anonymous";

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> replies;

}