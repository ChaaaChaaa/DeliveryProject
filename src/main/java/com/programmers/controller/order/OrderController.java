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
@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    public Order save(OrderRequestDto orderRequestDto){
        return orderService.save(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto searchOrderById(@PathVariable Long orderId){
        return orderService.findById(orderId);
    }

    @GetMapping("/search/user/{orderId}")
    public User searchUserByOrderId(@PathVariable Long orderId){
        return orderService.findUserByOrderId(orderId);
    }

    @GetMapping("/search/menu/{orderId}")
    public Menu searchMenuByOrderId(@PathVariable Long orderId){
        return orderService.findMenuByOrderId(orderId);
    }

    @GetMapping("/search/delivery/{orderId}")
    public Delivery searchDeliveryByOrderId(@PathVariable Long orderId){
        return orderService.findDeliveryByOrderId(orderId);
    }
}
