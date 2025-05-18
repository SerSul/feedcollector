package io.github.sersul.feedcollector.dto.response;

import lombok.*;

import java.time.LocalDateTime;

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
    private String authorUsername;
    private LocalDateTime createdAt;
}
