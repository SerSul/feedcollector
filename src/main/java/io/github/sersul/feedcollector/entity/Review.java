package io.github.sersul.feedcollector.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review extends BaseEntity {

    @Column(nullable = false, name = "title_p")
    String title;

    @Column(nullable = true, name = "description_p")
    String description;

    @Column(nullable = false, name = "content_p")
    String content;

    String url;

    @Range(min = 0, max = 5, message = "Rating must be between 0 and 5")
    @Column(name = "rating_p")
    Integer rating;

    @Column(name = "processed_p")
    boolean processed;

    @Column(nullable = true, name = "user_name_p")
    String userName = "Anonymous";

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "review")
    List<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER)
    List<Tag> tags;

}
