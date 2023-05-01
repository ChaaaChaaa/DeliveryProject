package com.programmers.controller.orderItem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.dto.orderItem.OrderItemResponseDto;
import com.programmers.service.orderItem.OrderItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/order-items")
@RestController
public class OrderItemController {
	private final OrderItemService orderItemService;

	@GetMapping("/{orderItemId}")
	public OrderItemResponseDto searchOrderItemByOrderItemId(@PathVariable Long orderItemId) {
		return orderItemService.findByOrderItemId(orderItemId);
	}
}
