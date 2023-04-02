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


}
