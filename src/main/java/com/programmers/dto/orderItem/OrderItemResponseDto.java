package com.programmers.dto.orderItem;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.storeMenu.StoreMenu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemResponseDto {
	private final Long orderItemId;
	private final OrderList orderList;
	private final StoreMenu storeMenu;
	private final Long quantity;
	private final int price;

	@Builder
	public OrderItemResponseDto(Long orderItemId, OrderList orderList, StoreMenu storeMenu, Long quantity, int price) {
		this.orderItemId = orderItemId;
		this.orderList = orderList;
		this.storeMenu = storeMenu;
		this.quantity = quantity;
		this.price = price;
	}

	public static OrderItemResponseDto of(OrderItem orderItem) {
		return OrderItemResponseDto.builder()
			.orderItemId(orderItem.getOrderItemId())
			.orderList(orderItem.getOrderList())
			.storeMenu(orderItem.getStoreMenu())
			.quantity(orderItem.getQuantity())
			.price(orderItem.getPrice())
			.build();
	}
}
