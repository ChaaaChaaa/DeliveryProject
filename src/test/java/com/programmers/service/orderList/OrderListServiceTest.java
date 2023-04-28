package com.programmers.service.orderList;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.order.OrderResponseDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.delivery.DeliveryService;
import com.programmers.service.user.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderListServiceTest {

    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    OrderListService orderListService;

    @Autowired
    UserService userService;

    @Autowired
    DeliveryService deliveryService;

    @BeforeEach
    void clean() {
//        orderRepository.deleteAll();
//        userRepository.deleteAll();
//        deliveryRepository.deleteAll();
    }

    @Test
    @DisplayName("저장된 주문 조회")
    void save() {
        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);

        assertEquals(basicOrderData(savedUser, savedDelivery).getUser().getUserName(),savedOrderList.getUser().getUserName());
    }

    @Test
    void findById() {
        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);

        OrderResponseDto orderResponseDto = orderListService.findById(savedOrderList.getOrderListId());

        assertNotNull(orderResponseDto);
        assertEquals("차차", savedOrderList.getUser().getNickName());
        assertEquals(Payment.CREDIT_CARD, savedOrderList.getPaymentMethod());
    }


    @Test
    void findDeliveryByOrderListId() {
        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);

        Delivery findDelivery = orderListService.findDeliveryByOrderListId(savedOrderList.getOrderListId());

        assertNotNull(findDelivery);
        assertEquals(savedOrderList.getDelivery().getDeliveryId(),findDelivery.getDeliveryId());
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

        orderListService.deleteById(savedOrderList.getOrderListId());

        //then
        Optional<OrderList> orderListId = orderListRepository.findByOrderListId(savedOrderList.getOrderListId());
        Assertions.assertTrue(orderListId.isEmpty());
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
                .phoneNumber("11111111111")
                .grade(Grade.NORMAL)
                .role(Role.CUSTOMER)
                .createdAt(LocalDateTime.now())
                .build();

    }
}