package com.programmers.controller.review;

import com.programmers.domain.review.Review;
import com.programmers.domain.store.Store;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.review.ReviewResponseDto;
import com.programmers.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/review")
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

    @GetMapping("/search")
    public List<Store> searchReviewByStore(@RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.findStoreById(reviewRequestDto);
    }


}
