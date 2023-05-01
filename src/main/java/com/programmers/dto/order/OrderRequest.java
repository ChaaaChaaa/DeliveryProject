package com.programmers.dto.order;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long userId;
    private Long deliveryId;
    private Payment paymentMethod;
    private OrderState orderState;
    private int totalPrice;
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems(Long orderListId) {
        return orderItems.stream()
                .map(orderItem -> orderItem.toOrderItem(new OrderList(orderListId, null, null, null, null, 0)))
                .collect(Collectors.toList());
    }
}
