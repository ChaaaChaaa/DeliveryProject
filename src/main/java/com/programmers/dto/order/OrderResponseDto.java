package com.programmers.dto.order;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.menu.Menu;
import com.programmers.domain.order.Order;
import com.programmers.domain.user.User;

import lombok.Builder;


public class OrderResponseDto {
    private final Long orderId;
    private final User user;
    private final Menu menu;
    private final Delivery delivery;
    private final String paymentMethod;
    private final String state;
    private final int price;

    @Builder
    public OrderResponseDto(Long orderId, User user, Menu menu, Delivery delivery, String paymentMethod, String state, int price) {
        this.orderId = orderId;
        this.user = user;
        this.menu = menu;
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.state = state;
        this.price = price;
    }

    public static OrderResponseDto of(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .user(order.getUser())
                .menu(order.getMenu())
                .delivery(order.getDelivery())
                .paymentMethod(order.getPaymentMethod())
                .state(order.getState())
                .price(order.getPrice())
                .build();
    }
}
