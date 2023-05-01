package com.programmers.dto.order;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.user.User;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private User user;
    @NotNull
    private Delivery delivery;
    @NotNull
    private Payment paymentMethod;
    @NotNull
    private OrderState orderState;
    @NotNull
    private int totalPrice;

    @NotNull
    private List<OrderItem> orderItems; //주문 항목에 대한 list가 이미 있기때문에

    @Builder
    public OrderRequestDto(User user, Delivery delivery, Payment paymentMethod, OrderState orderState, int totalPrice, List<OrderItem> orderItems) {
        this.user = user;
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public static OrderRequestDto of(OrderList orderList) {
        return OrderRequestDto.builder()
                .user(orderList.getUser())
                .delivery(orderList.getDelivery())
                .paymentMethod(orderList.getPaymentMethod())
                .orderState(orderList.getOrderState())
                .totalPrice(orderList.getTotalPrice())
                .build();
    }
}
