package io.github.sersul.feedcollector.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StandardResponse{
    boolean success;
    String message;
    Object data;

    public StandardResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
