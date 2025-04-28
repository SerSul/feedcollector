package io.github.sersul.feedcollector.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateReviewDto {
    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 100, message = "Название слишком длинное")
    private String title;

    @Size(max = 200, message = "Описание слишком длинное")
    private String description;

    @NotBlank(message = "URL не может быть пустым")
    @Pattern(regexp = "^(http|https)://.*", message = "Некорректный URL")
    private String url;

    @Min(value = 1, message = "Оценка должна быть от 1 до 5")
    @Max(value = 5, message = "Оценка должна быть от 1 до 5")
    private int rating;

    @NotBlank(message = "Текст отзыва не может быть пустым")
    private String content;
}