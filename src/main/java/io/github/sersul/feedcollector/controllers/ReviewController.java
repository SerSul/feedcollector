package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.dto.request.AddCommentDTO;
import io.github.sersul.feedcollector.dto.request.CreateReviewDto;
import io.github.sersul.feedcollector.dto.response.ReviewDto;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.service.CommentsService;
import io.github.sersul.feedcollector.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final CommentsService commentsService;

    @PostMapping("/create-review")
    public ResponseEntity<StandardResponse> createReview(@Valid @RequestBody CreateReviewDto createReviewDto) {
        return reviewService.createReview(createReviewDto);
    }

    @PostMapping("/add-comment")
    public ResponseEntity<StandardResponse> addComment(@Valid @RequestBody AddCommentDTO createReviewDto) {
        return commentsService.addComment(createReviewDto);
    }

    @GetMapping
    public ResponseEntity<Page<ReviewDto>> getAllReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ReviewDto> pageResult = reviewService.getAllReviews(pageable);
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ReviewDto>> searchReviewsByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewDto> pageResult = reviewService.findByTitleContaining(title, pageable);
        return ResponseEntity.ok(pageResult);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        var review = reviewService.getReviewById(id);
        return review
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
