package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.dto.request.AddCommentDTO;
import io.github.sersul.feedcollector.dto.request.CreateReviewDto;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.service.CommentsService;
import io.github.sersul.feedcollector.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final CommentsService commentsService;

    @PostMapping("/create-review")
    public ResponseEntity<StandardResponse> createReview(CreateReviewDto createReviewDto) {
        return reviewService.createReview(createReviewDto);
    }

    @PostMapping("/add-comment")
    public ResponseEntity<StandardResponse> addComment(AddCommentDTO createReviewDto) {
        return commentsService.addComment(createReviewDto);
    }


}
