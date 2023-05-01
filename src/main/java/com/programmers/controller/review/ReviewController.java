package com.programmers.controller.review;

import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.review.ReviewResponseDto;
import com.programmers.service.review.ReviewService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.save(reviewRequestDto);
    }

    @GetMapping("/search/{reviewId}")
    public ReviewResponseDto searchReviewById(@PathVariable Long reviewId) {
        return reviewService.findById(reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteById(reviewId);
    }
}
