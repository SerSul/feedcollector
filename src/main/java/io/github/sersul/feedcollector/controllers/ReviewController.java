package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.entity.Comment;
import io.github.sersul.feedcollector.entity.Review;
import io.github.sersul.feedcollector.repository.CommentRepository;
import io.github.sersul.feedcollector.repository.ReviewRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/")
    public String home(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @CookieValue(value = "username", required = false) String username
    ) {
        Page<Review> reviewPage = reviewRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("reviews", reviewPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reviewPage.getTotalPages());
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/api/reviews/search")
    public String searchReviews(
            @RequestParam String title,
            Model model
    ) {
        var reviews = reviewRepository.findByTitleContainingIgnoreCase(title);
        model.addAttribute("reviews", reviews);
        return "fragments/review-list :: review-list";
    }

    @GetMapping("/reviews/{id}")
    public String viewReview(@PathVariable Long id,
                             @CookieValue(value = "username", required = false) String userName,
                             Model model) {
        var review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отзыв не найден"));
        model.addAttribute("review", review);
        model.addAttribute("username", userName);
        return "review_page";
    }

    @GetMapping("/reviews/create")
    public String createReview(Model model,
                               @CookieValue(value = "username", required = false) String userName
    ) {

        return "fragments/add-review-page";
    }

    // Добавление комментария
    @PostMapping("/reviews/{reviewId}/comments")
    public String addComment(@PathVariable Long reviewId,
                             @RequestParam String userComment,
                             @RequestParam(required = false) Long parentCommentId,
                             @CookieValue(value = "username", required = false) String userName) {

        var review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Отзыв не найден"));

        var comment = new Comment();
        comment.setContent(userComment);
        comment.setReview(review);
        comment.setUserName(StringUtils.isNotBlank(userName) ? userName : "Anonymous");

        if (parentCommentId != null) {
            var parentComment = commentRepository.findById(parentCommentId)
                    .orElseThrow(() -> new EntityNotFoundException("Родительский комментарий не найден"));
            comment.setParentComment(parentComment);
        }

        commentRepository.save(comment);

        return "redirect:/reviews/" + reviewId;
    }
}