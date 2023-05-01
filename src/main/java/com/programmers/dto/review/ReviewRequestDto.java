package com.programmers.dto.review;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.review.Review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long reviewId;
    @NotNull
    private OrderList orderList;
    @NotBlank
    private float rating;
    private String content;
    private String reviewPicture;

    @Builder
    public ReviewRequestDto(OrderList orderList, float rating, String content, String reviewPicture) {
        this.orderList = orderList;
        this.rating = rating;
        this.content = content;
        this.reviewPicture = reviewPicture;
    }

    public static ReviewRequestDto of(Review review) {
        return ReviewRequestDto.builder()
                .orderList(review.getOrderList())
                .rating(review.getRating())
                .content(review.getContent())
                .reviewPicture(review.getReviewPicture())
                .build();
    }

    public Review toEntity() {
        return Review.builder()
                .orderList(orderList)
                .rating(rating)
                .content(content)
                .reviewPicture(reviewPicture)
                .build();
    }
}
