package com.programmers.controller.order;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.order.OrderResponseDto;
import com.programmers.service.orderList.OrderListService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/order-list")
@RestController
public class OrderController {
    private final OrderListService orderListService;

    @PostMapping("/save")
    public OrderList save(OrderRequestDto orderRequestDto) {
        return orderListService.save(orderRequestDto);
    }

    @GetMapping("/{orderListId}")
    public OrderResponseDto searchOrderById(@PathVariable Long orderListId) {
        return orderListService.findById(orderListId);
    }

    @GetMapping("/search/user/{orderListId}")
    public User searchUserByOrderListId(@PathVariable Long orderListId) {
        return orderListService.findUserByOrderListId(orderListId);
    }


    @GetMapping("/search/delivery/{orderListId}")
    public Delivery searchDeliveryByOrderListId(@PathVariable Long orderListId) {
        return orderListService.findDeliveryByOrderListId(orderListId);
    }
}
