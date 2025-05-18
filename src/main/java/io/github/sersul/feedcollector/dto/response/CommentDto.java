package io.github.sersul.feedcollector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    @JsonProperty("author_user_name")
    private String authorUsername;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    private String content;
    @JsonProperty("parent_comment_id")
    private Long parentCommentId;
}
