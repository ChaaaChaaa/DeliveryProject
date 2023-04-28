package com.programmers.service.review;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.food.Food;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.review.Review;
import com.programmers.domain.store.Store;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.orderItem.OrderItemRequestDto;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.food.FoodRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.review.ReviewRepository;
import com.programmers.repository.store.StoreRepository;
import com.programmers.repository.storeMenu.StoreMenuRepository;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.delivery.DeliveryService;
import com.programmers.service.food.FoodService;
import com.programmers.service.orderList.OrderListService;
import com.programmers.service.store.StoreService;
import com.programmers.service.user.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    OrderListRepository orderListRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreMenuRepository storeMenuRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    FoodService foodService;
    @Autowired
    StoreService storeService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    OrderListService orderListService;

    @Autowired
    UserService userService;

    @Autowired
    DeliveryService deliveryService;

    @BeforeEach
    void clean() {

    }

    @Test
    @DisplayName("저장된 리뷰 조회")
    void save() {
        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);

        Review review = basicReviewData(savedOrderList);
        Review savedReview = reviewRepository.save(review);
        assertEquals(savedReview.getOrderList(), review.getOrderList());
    }


    @Test
    void findById() {
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);

        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        StoreMenu storeMenu = StoreMenu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();
        StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);


        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);

        OrderItem orderItem = basicOrderItemData(savedOrderList, savedStoreMenu);
        OrderItemRequestDto orderItemRequestDto = OrderItemRequestDto.of(orderItem);
        OrderItem savedOrderItem = orderItemRepository.save(orderItemRequestDto.toEntity(orderItemRequestDto));


        Review review = basicReviewData(savedOrderList);
        ReviewRequestDto reviewRequestDto = ReviewRequestDto.of(review);
        Review savedReview = reviewService.save(reviewRequestDto);

        //when
        List<OrderItem> orderItems = reviewService.findOrderItemByOrderListId(savedReview.getReviewId());

        //then
        assertEquals("차차네", orderItems.get(0).getStoreMenu().getStore().getStoreName());
    }


    @Test
    void deleteById() {
        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);

        Review review = basicReviewData(savedOrderList);
        Review savedReview = reviewService.save(ReviewRequestDto.of(review));
        reviewRepository.delete(savedReview);

        Optional<Review> findId = reviewRepository.findById(savedReview.getReviewId());
        Assertions.assertTrue(findId.isEmpty());
    }

    private Review basicReviewData(OrderList orderList) {
        return Review.builder()
                .orderList(orderList)
                .rating(5.0F)
                .content("정말 맛있어요!")
                .build();
    }

    private OrderItem basicOrderItemData(OrderList savedOrderList, StoreMenu savedStoreMenu) {
        return OrderItem.builder()
                .orderList(savedOrderList)
                .storeMenu(savedStoreMenu)
                .quantity(2L)
                .price(1000)
                .build();
    }

    private OrderList basicOrderData(User user, Delivery delivery) {
        return OrderList.builder()
                .user(user)
                .delivery(delivery)
                .paymentMethod(Payment.CREDIT_CARD)
                .orderState(OrderState.READY)
                .totalPrice(10000)
                .build();
    }

    private Delivery basicDelivery() {
        return Delivery.builder()
                .build();
    }

    private User basicUserData() {
        return User.builder()
                .userName("test")
                .password("1234")
                .nickName("차차")
                .phoneNumber("01011111111")
                .grade(Grade.NORMAL)
                .role(Role.CUSTOMER)
                .createdAt(LocalDateTime.now())
                .build();

    }

    private StoreMenu basicMenuData() {
        return StoreMenu.builder()
                .food(basicFoodData())
                .store(basicStoreData())
                .build();
    }

    private Food basicFoodData() {
        return Food.builder()
                .price(1000)
                .description("맛있는라면")
                .name("라면")
                .build();
    }

    private Store basicStoreData() {
        return Store.builder()
                .category("noodle")
                .storeName("차차네")
                .rating(5.0f)
                .reviewCount(100)
                .build();
    }
}