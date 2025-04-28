package io.github.sersul.feedcollector.service;


import io.github.sersul.feedcollector.dto.CreateReviewDto;
import io.github.sersul.feedcollector.entity.Review;
import io.github.sersul.feedcollector.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void createReview(CreateReviewDto dto, String username) {
        Review review = new Review();
        review.setTitle(dto.getTitle());
        review.setDescription(dto.getDescription());
        review.setCreatedAt(LocalDateTime.now());
        review.setUrl(dto.getUrl());
        review.setRating(dto.getRating());
        review.setContent(dto.getContent());
        review.setUserName(username);

        reviewRepository.save(review);
    }
}