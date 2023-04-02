package com.programmers.service.review;

import com.programmers.domain.menu.Menu;
import com.programmers.domain.order.Order;
import com.programmers.domain.review.Review;
import com.programmers.domain.store.Store;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.review.ReviewResponseDto;
import com.programmers.repository.menu.MenuRepository;
import com.programmers.repository.order.OrderRepository;
import com.programmers.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    List<Store> stores;

    public Review save(ReviewRequestDto reviewRequestDto) {
        return reviewRepository.save(reviewRequestDto.toEntity());
    }

    public List<Store> findStoreById(ReviewRequestDto reviewRequestDto){
        stores = new ArrayList<>();
        Order order = orderRepository.findById(reviewRequestDto.getOrder().getOrderId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 주문이 존재하지 않습니다."));
        Menu menu = menuRepository.findById(order.getMenu().getMenuId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 메뉴가 존재하지 않습니다."));
        stores.add(menu.getStore());
        return stores;
    }

    public ReviewResponseDto findById(Long reviewId) {
        return ReviewResponseDto.of(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 리뷰가 존재하지 않습니다.")));
    }

    public void deleteById(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
