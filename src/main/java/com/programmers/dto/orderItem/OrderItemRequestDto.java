package com.programmers.dto.orderItem;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.storeMenu.StoreMenu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestDto {
    private final OrderList orderList;
    private final StoreMenu storeMenu;
    private final Long quantity;
    private final int price;
    private Long orderItemId;

    @Builder
    public OrderItemRequestDto(OrderList orderList, StoreMenu storeMenu, Long quantity, int price) {
        this.orderList = orderList;
        this.storeMenu = storeMenu;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItem toEntity(OrderItemRequestDto orderItemRequestDto) {
        return OrderItem.builder()
                .orderList(orderItemRequestDto.orderList)
                .storeMenu(orderItemRequestDto.storeMenu)
                .quantity(orderItemRequestDto.getQuantity())
                .price(orderItemRequestDto.getPrice())
                .build();
    }

    public static OrderItemRequestDto of(OrderItem orderItem) {
        return OrderItemRequestDto.builder()
                .orderList(orderItem.getOrderList())
                .storeMenu(orderItem.getStoreMenu())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
}
