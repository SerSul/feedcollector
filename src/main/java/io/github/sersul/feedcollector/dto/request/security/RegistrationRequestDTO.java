package io.github.sersul.feedcollector.dto.request.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class RegistrationRequestDTO {

    @NotBlank
    @JsonProperty("user_name")
    private String userName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    // геттеры/сеттеры
}