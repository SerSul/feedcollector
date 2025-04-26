package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.entity.Review;
import io.github.sersul.feedcollector.repository.ReviewRepository;
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

    @GetMapping("/")
    public String home(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Review> reviewPage = reviewRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("reviews", reviewPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reviewPage.getTotalPages());
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
}