package com.programmers.service.review;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.review.Review;
import com.programmers.domain.store.Store;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.review.ReviewResponseDto;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.review.ReviewRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderListRepository orderListRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Review save(ReviewRequestDto reviewRequestDto) {
        return reviewRepository.save(reviewRequestDto.toEntity());
    }

    public ReviewResponseDto findById(Long reviewId) {
        return ReviewResponseDto.of(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 리뷰가 존재하지 않습니다.")));
    }

    private List<Store> findStoresByOrderItems(List<OrderItem> orderItems) {
        List<Store> stores = orderItems.stream()
                .map(orderItem -> {
                    StoreMenu storeMenu = orderItem.getStoreMenu();
                    return storeMenu.getStore();
                })
                .collect(Collectors.toList());
        return stores;
    }

    @Transactional
    public List<OrderItem> findOrderItemByOrderListId(Long reviewId) {
        Review review = reviewRepository.findByReviewId(reviewId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 리뷰가 존재하지 않습니다."));
        OrderList orderList = orderListRepository.findByOrderListId(review.getOrderList().getOrderListId()).orElseThrow(() -> new NoSuchElementException("해당 id의 주문서가 존재하지 않습니다."));
        List<OrderItem> orderItems = orderItemRepository.findByOrderListId(orderList.getOrderListId()).stream().collect(Collectors.toList());
        return orderItems;
    }

    public void deleteById(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
