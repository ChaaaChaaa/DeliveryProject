package com.programmers.dto.orderItem;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.storeMenu.StoreMenu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemRequestDto {
    private OrderList orderList;
    private StoreMenu storeMenu;
    private Long quantity;
    private int price;
    private Long orderItemId;

    @Builder
    public OrderItemRequestDto(OrderList orderList, StoreMenu storeMenu, Long quantity, int price) {
        this.orderList = orderList;
        this.storeMenu = storeMenu;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItemRequestDto of(OrderItem orderItem) {
        return OrderItemRequestDto.builder()
                .orderList(orderItem.getOrderList())
                .storeMenu(orderItem.getStoreMenu())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .orderList(orderList)
                .storeMenu(storeMenu)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
