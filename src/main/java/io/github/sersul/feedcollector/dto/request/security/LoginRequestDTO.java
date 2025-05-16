package io.github.sersul.feedcollector.dto.request.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LoginRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
