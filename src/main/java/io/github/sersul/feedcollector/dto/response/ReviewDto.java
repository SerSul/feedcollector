package io.github.sersul.feedcollector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private Long id;
    private String title;
    private String content;
    private String description;
    private String url;
    private int rating;
    @JsonProperty("author_user_name")
    private String authorUsername;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    List<CommentDto> comments;
}
