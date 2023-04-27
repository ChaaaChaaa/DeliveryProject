package com.programmers.dto.review;

import com.programmers.domain.order.Order;
import com.programmers.domain.review.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    @NotNull
    private Long reviewId;
    @NotNull
    private Order order;
    @NotBlank
    private float rating;
    private String content;
    private String reviewPicture;

    @Builder
    public ReviewRequestDto(Long reviewId, Order order, float rating, String content, String reviewPicture) {
        this.reviewId = reviewId;
        this.order = order;
        this.rating = rating;
        this.content = content;
        this.reviewPicture = reviewPicture;
    }

    public static ReviewRequestDto of(Review review){
        return ReviewRequestDto.builder()
                .reviewId(review.getReviewId())
                .order(review.getOrder())
                .rating(review.getRating())
                .content(review.getContent())
                .reviewPicture(review.getReviewPicture())
                .build();
    }

    public Review toEntity(){
        return Review.builder()
                .reviewId(reviewId)
                .order(order)
                .rating(rating)
                .content(content)
                .reviewPicture(reviewPicture)
                .build();
    }


}
