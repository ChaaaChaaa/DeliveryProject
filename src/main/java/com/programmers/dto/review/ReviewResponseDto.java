package com.programmers.dto.review;

import com.programmers.domain.order.Order;
import com.programmers.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private Order order;
    private float rating;
    private String content;
    private String reviewPicture;

    @Builder
    public ReviewResponseDto(Long reviewId, Order order, float rating, String content, String reviewPicture) {
        this.reviewId = reviewId;
        this.order = order;
        this.rating = rating;
        this.content = content;
        this.reviewPicture = reviewPicture;
    }

    public static ReviewResponseDto of(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .order(review.getOrder())
                .rating(review.getRating())
                .content(review.getContent())
                .reviewPicture(review.getReviewPicture())
                .build();
    }
}
