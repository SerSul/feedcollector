package io.github.sersul.feedcollector.service;


import io.github.sersul.feedcollector.dto.request.CreateReviewDto;
import io.github.sersul.feedcollector.dto.response.ReviewDto;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.entity.Review;
import io.github.sersul.feedcollector.repository.ReviewRepository;
import io.github.sersul.feedcollector.repository.UserRepository;
import io.github.sersul.feedcollector.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final UserContext userContext;

    public ResponseEntity<StandardResponse> createReview(@RequestBody @Valid CreateReviewDto dto) {
        var user = userContext.getCurrentUser();
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        var review = Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .url(dto.getUrl())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .user(user)
                .build();

        reviewRepository.save(review);

        return ResponseEntity.ok(new StandardResponse(true, "Отзыв успешно создан"));
    }

    public Page<ReviewDto> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(Review::toDto);
    }

    public Optional<ReviewDto> getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .authorUsername(review.getUser().getUsername())
                        .createdAt(review.getCreatedAt())
                        .url(review.getUrl())
                        .title(review.getTitle())
                        .rating(review.getRating())
                        .description(review.getDescription())
                        .content(review.getContent())
                        .build()
                );
    }


}