package com.programmers.service.orderItem;

import com.programmers.domain.orderItem.OrderItem;
import com.programmers.dto.orderItem.OrderItemResponseDto;
import com.programmers.repository.orderItem.OrderItemRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemResponseDto findByOrderItemId(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findByOrderItemId(orderItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 OrderItem이 존재하지 않습니다."));
        return OrderItemResponseDto.of(orderItem);
    }

//    public OrderList findOrderListIdByOrderItemId(Long orderItemId) {
//        OrderItem orderItem = orderItemRepository.findByOrderItemId(orderItemId)
//                .orElseThrow(() -> new NoSuchElementException("해당 id를 가진 주문이 없습니다."));
//        return orderItem.getOrderList();
//    }
//
//    public List<StoreMenu> findStoreMenuIdByOrderItemId(Long orderItemId) {
//        OrderItem orderItem = orderItemRepository.findByOrderItemId(orderItemId).orElseThrow(() -> new NoSuchElementException("해당 id를 가진 주문이 없습니다."));
//        return storeMenuRepository.findStoreListByStoreMenuId(orderItem.getStoreMenu().getStoreMenuId());
//    }
//
//
//    public List<OrderItem> findOrderItemsByOrderListId(Long orderItemId) {
//        List<OrderItem> orderItems = orderItemRepository.findByOrderItemId(orderItemId).stream().collect(Collectors.toList());
//        return orderItems;
//    }

    public void deleteByOrderItemId(Long orderItemId) {
        orderItemRepository.deleteByOrderItemId(orderItemId);
    }
}
