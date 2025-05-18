package io.github.sersul.feedcollector.entity;

import io.github.sersul.feedcollector.dto.response.ReviewDto;
import io.github.sersul.feedcollector.entity.security.User;
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
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "review")
    List<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER)
    List<Tag> tags;

    public ReviewDto toDto() {
        return ReviewDto.builder()
                .id(this.getId())
                .title(this.getTitle())
                .content(this.getContent())
                .rating(this.getRating())
                .description(this.getDescription())
                .url(this.getUrl())
                .createdAt(this.getCreatedAt())
                .authorUsername(this.getUser().getUsername())
                .build();
    }

}
