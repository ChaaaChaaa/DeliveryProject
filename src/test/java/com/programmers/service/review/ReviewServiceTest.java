package com.programmers.service.review;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.food.Food;
import com.programmers.domain.menu.Menu;
import com.programmers.domain.order.Order;
import com.programmers.domain.review.Review;
import com.programmers.domain.store.Store;
import com.programmers.domain.user.User;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.review.ReviewResponseDto;
import com.programmers.repository.review.ReviewRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @BeforeEach
    void clean() {
        if (reviewRepository != null) {
            reviewRepository.deleteAll();
        }
    }

    @Test
    @DisplayName("저장된 리뷰 조회")
    void save() {
        Review review = basicReviewData();
        Review savedReview = reviewRepository.save(review);
        assertEquals(savedReview.getOrder(), review.getOrder());
    }

    @Test
    @DisplayName("Id로 가게조회")
    void findStoreById() {
        Review review = basicReviewData();
        Review savedReview = reviewRepository.save(review);

        //when
        ReviewRequestDto reviewRequestDto = ReviewRequestDto.of(savedReview);
        List<Store> stores = reviewService.findStoreById(reviewRequestDto);

        //then
        assertEquals(1, stores.size());
        assertEquals("차차네", stores.get(0));
    }

    @Test
    void findById() {
        Review review = basicReviewData();
        Review savedReview = reviewRepository.save(review);

        //when
        ReviewRequestDto reviewRequestDto = ReviewRequestDto.of(savedReview);
        reviewService.findStoreById(reviewRequestDto);

        //then
        assertNotNull(reviewRequestDto);
        assertEquals(5.0F, reviewRequestDto.getRating());
        assertEquals("정말 맛있어요!", reviewRequestDto.getContent());
    }


    @Test
    void deleteById() {
        Review review = basicReviewData();
        Review savedReview = reviewService.save(ReviewRequestDto.of(review));

        reviewRepository.delete(savedReview);

        Optional<Review> findId = reviewRepository.findById(savedReview.getReviewId());
        Assertions.assertTrue(findId.isEmpty());
    }

    private Review basicReviewData() {
        return Review.builder()
                .reviewId(1L)
                .order(basicOrderData())
                .rating(5.0F)
                .content("정말 맛있어요!")
                .build();
    }

    private Order basicOrderData() {
        return Order.builder()
                .orderId(1L)
                .user(basicUserData())
                .menu(basicMenuData())
                .delivery(basicDelivery())
                .paymentMethod("카드")
                .state("음식 준비중")
                .price(10000)
                .build();
    }

    private Delivery basicDelivery() {
        return Delivery.builder()
                .deliveryId(1L)
                .deliveryState("배달중")
                .build();
    }

    private User basicUserData() {
        return User.builder()
                .userId(1L)
                .name("test")
                .password("1234")
                .nickName("차차")
                .grade("Customer")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

    }

    private Menu basicMenuData() {
        return Menu.builder()
                .menuId(1L)
                .food(basicFoodData())
                .store(basicStoreData())
                .build();
    }

    private Food basicFoodData() {
        return Food.builder()
                .id(1L)
                .price(1000)
                .description("맛있는라면")
                .name("라면")
                .build();
    }

    private Store basicStoreData() {
        return Store.builder()
                .storeId(1L)
                .category("noodle")
                .storeName("차차네")
                .rating(5.0f)
                .reviewCount(100)
                .build();
    }
}