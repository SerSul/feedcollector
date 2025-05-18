package io.github.sersul.feedcollector.service;

import io.github.sersul.feedcollector.dto.request.AddCommentDTO;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.entity.Comment;
import io.github.sersul.feedcollector.repository.CommentRepository;
import io.github.sersul.feedcollector.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentsService {
    private final CommentRepository commentRepository;
    private final UserContext userContext;
    private final ReviewRepository reviewRepository;


    public ResponseEntity<StandardResponse> addComment(AddCommentDTO request) {
        var user = userContext.getCurrentUser();
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var review = reviewRepository.findById(request.getReviewId());
        if (review.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Optional<Comment> parentComment = Optional.empty();
        if (request.getParentCommentId() != null) {
            parentComment = commentRepository.findById(request.getParentCommentId());
        }

        var comment = Comment.builder()
                .parentComment(parentComment.orElse(null))
                .content(request.getContent())
                .user(user)
                .review(review.get())
                .build();

        commentRepository.save(comment);
        return ResponseEntity.ok().body(new StandardResponse(true, "created"));
    }

}
