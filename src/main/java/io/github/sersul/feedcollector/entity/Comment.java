package io.github.sersul.feedcollector.entity;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {

    @Column(name = "content_p")
    String content;

    @ManyToOne
    @JoinColumn(name = "review_id")
    Review review;

    @Column(nullable = true, name = "user_name_p")
    String userName = "Anonymous";

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Comment parentComment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentComment")
    List<Comment> replies;

}