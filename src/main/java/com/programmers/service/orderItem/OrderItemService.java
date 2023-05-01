package com.programmers.service.orderItem;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.programmers.domain.orderItem.OrderItem;
import com.programmers.dto.orderItem.OrderItemRequestDto;
import com.programmers.dto.orderItem.OrderItemResponseDto;
import com.programmers.repository.orderItem.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderItemService {
	private final OrderItemRepository orderItemRepository;

	@Transactional
	public OrderItem save(OrderItemRequestDto orderItemRequestDto) {
		return orderItemRepository.save(orderItemRequestDto.toEntity());
	}

	@Transactional
	public OrderItemResponseDto findByOrderItemId(Long orderItemId) {
		OrderItem orderItem = orderItemRepository.findByOrderItemId(orderItemId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 OrderItem이 존재하지 않습니다."));
		return OrderItemResponseDto.of(orderItem);
	}

}
