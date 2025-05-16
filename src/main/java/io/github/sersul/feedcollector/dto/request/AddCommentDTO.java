package io.github.sersul.feedcollector.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class AddCommentDTO {

    @NotNull
    String content;

    @JsonProperty("review_id")
    @NotNull
    Long reviewId;

    @JsonProperty("parent_comment_id")
    Long parentCommentId;
}
