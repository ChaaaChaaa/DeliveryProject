package com.programmers.service.review;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.review.Review;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.review.ReviewResponseDto;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.review.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final OrderListRepository orderListRepository;
	private final OrderItemRepository orderItemRepository;

	@Transactional
	public Review save(ReviewRequestDto reviewRequestDto) {
		Review review = reviewRequestDto.toEntity();
		OrderList orderList = review.getOrderList();
		orderList = orderListRepository.save(orderList);
		review.setOrderList(orderList);
		return reviewRepository.save(review);
	}

	public ReviewResponseDto findById(Long reviewId) {
		return ReviewResponseDto.of(reviewRepository.findById(reviewId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 리뷰가 존재하지 않습니다.")));
	}

	@Transactional
	public List<OrderItem> findOrderItemByOrderListId(Long reviewId) {
		Review review = reviewRepository.findByReviewId(reviewId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 리뷰가 존재하지 않습니다."));
		OrderList orderList = orderListRepository.findByOrderListId(review.getOrderList().getOrderListId())
			.orElseThrow(() -> new NoSuchElementException("해당 id의 주문서가 존재하지 않습니다."));
		return new ArrayList<>(orderItemRepository.findByOrderListId(orderList.getOrderListId()));
	}

	public void deleteById(long reviewId) {
		reviewRepository.deleteById(reviewId);
	}
}
