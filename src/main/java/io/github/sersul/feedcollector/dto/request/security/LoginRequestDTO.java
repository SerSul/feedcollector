package io.github.sersul.feedcollector.dto.request.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LoginRequestDTO {

    @NotBlank
    @JsonProperty("user_name")
    private String userName;

    @NotBlank
    private String password;

}
