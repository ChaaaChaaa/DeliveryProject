package com.programmers.service.order;

import com.programmers.domain.order.Order;
import com.programmers.repository.order.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @BeforeEach
    void clean(){
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("저장된 주문 조회")
    void save() {
//        Order order = basicOrderData();
//        Order savedOrder = orderRepository.save(O)
    }

    @Test
    void findById() {
    }

    @Test
    void findUserByOrderId() {
    }

    @Test
    void findMenuByOrderId() {
    }

    @Test
    void findDeliveryByOrderId() {
    }

    @Test
    void deleteById() {
    }
}