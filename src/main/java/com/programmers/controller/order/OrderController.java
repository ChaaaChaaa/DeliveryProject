package com.programmers.controller.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequest;
import com.programmers.dto.order.OrderResponseDto;
import com.programmers.service.orderList.OrderListService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/order-list")
@RestController
public class OrderController {
	private final OrderListService orderListService;

	@PostMapping("/save")
	public void save(@RequestBody OrderRequest orderRequest) {
		orderListService.save(orderRequest);
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
