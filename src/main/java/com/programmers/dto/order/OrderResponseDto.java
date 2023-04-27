package com.programmers.dto.order;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.user.User;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private final Long orderId;
    private final User user;
    private final Delivery delivery;
    private final Payment paymentMethod;
    private final OrderState orderState;
    private final int totalPrice;
    private final List<OrderItem> orderItems;

    @Builder
    public OrderResponseDto(Long orderId, User user, Delivery delivery, Payment paymentMethod, OrderState orderState, int totalPrice, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.user = user;
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }


    public static OrderResponseDto of(OrderList orderList) {
        return OrderResponseDto.builder()
                .orderId(orderList.getOrderListId())
                .user(orderList.getUser())
                .delivery(orderList.getDelivery())
                .paymentMethod(orderList.getPaymentMethod())
                .orderState(orderList.getOrderState())
                .totalPrice(orderList.getTotalPrice())
                .orderItems(orderList.getOrderItems())
                .build();
    }
}
