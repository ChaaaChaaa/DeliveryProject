package com.programmers.controller.review;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.programmers.controller.order.OrderController;
import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.review.Review;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.review.ReviewRequestDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.review.ReviewRepository;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.delivery.DeliveryService;
import com.programmers.service.food.FoodService;
import com.programmers.service.orderItem.OrderItemService;
import com.programmers.service.orderList.OrderListService;
import com.programmers.service.review.ReviewService;
import com.programmers.service.store.StoreService;
import com.programmers.service.storeMenu.StoreMenuService;
import com.programmers.service.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ReviewControllerTest {
    @Autowired
    OrderListRepository orderListRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    OrderListService orderListService;
    @Autowired
    UserService userService;
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    ReviewService reviewService;
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ReviewController(reviewService)).build();
        reviewRepository.deleteAll();
    }


    @Test
    void saveReview() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
//        OrderList savedOrderList = orderListService.save(orderRequestDto);
//
//        Review review =basicReviewData(savedOrderList);
//        ReviewRequestDto reviewRequestDto = ReviewRequestDto.of(review);
//        Review savedReview = reviewService.save(reviewRequestDto);
//
//        String json = objectMapper.writeValueAsString(savedReview);
//        System.out.println(json);
//        mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.reviewId").value(savedReview.getReviewId()))
//                .andExpect(jsonPath("$.user.userId").value(savedReview.getOrderList().getOrderListId()))
//                .andExpect(jsonPath("$.content").value(savedReview.getContent()))
//                .andExpect(jsonPath("$.rating").value(savedReview.getRating()));

    }

    @Test
    void deleteReview() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
//        OrderList savedOrderList = orderListService.save(orderRequestDto);
//
//        Review review =basicReviewData(savedOrderList);
//        ReviewRequestDto reviewRequestDto = ReviewRequestDto.of(review);
//        Review savedReview = reviewService.save(reviewRequestDto);
//
//        mockMvc.perform(delete("/reviews/{reviewId}", savedReview.getReviewId()))
//                .andExpect(status().isOk())
//                .andDo(print());
//        assertEquals(0L, reviewRepository.count());
    }

    private OrderList basicOrderData(User user, Delivery delivery) {
        return OrderList.builder()
                .user(user)
                .delivery(delivery)
                .paymentMethod(Payment.CREDIT_CARD)
                .orderState(OrderState.SHIPPING)
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
                .phoneNumber("11111111111")
                .grade(Grade.NORMAL)
                .role(Role.CUSTOMER)
                .build();
    }


    private Review basicReviewData(OrderList orderList){
        return Review.builder()
                .orderList(orderList)
                .rating(5.0F)
                .content("맛있어요!")
                .build();
    }
}
