package com.programmers.dto.review;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.review.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
	private Long reviewId;
	private OrderList orderList;
	private float rating;
	private String content;
	private String reviewPicture;

	@Builder
	public ReviewResponseDto(Long reviewId, OrderList orderList, float rating, String content, String reviewPicture) {
		this.reviewId = reviewId;
		this.orderList = orderList;
		this.rating = rating;
		this.content = content;
		this.reviewPicture = reviewPicture;
	}

	public static ReviewResponseDto of(Review review) {
		return ReviewResponseDto.builder()
			.reviewId(review.getReviewId())
			.orderList(review.getOrderList())
			.rating(review.getRating())
			.content(review.getContent())
			.reviewPicture(review.getReviewPicture())
			.build();
	}
}
