package io.github.sersul.feedcollector.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StandardResponse {
    boolean success;
    String message;
}
