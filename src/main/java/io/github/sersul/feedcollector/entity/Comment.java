package io.github.sersul.feedcollector.entity;

import io.github.sersul.feedcollector.entity.security.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {

    @Column(name = "content_p")
    String content;

    @ManyToOne
    @JoinColumn(name = "review_id")
    Review review;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentComment")
    List<Comment> replies;

}