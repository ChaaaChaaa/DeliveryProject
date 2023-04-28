package com.programmers.controller.orderItem;

import com.programmers.dto.orderItem.OrderItemResponseDto;
import com.programmers.service.orderItem.OrderItemService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @GetMapping("/{orderItemId}/order-list")
//    public OrderList searchOrderListByOrderItemId(@PathVariable Long orderItemId){
//      return orderItemService.findOrderListIdByOrderItemId(orderItemId);
//    }
//
//    @GetMapping("/{orderItemId}/store-menus")
//    public List<StoreMenu> searchStoreMenuByOrderItemId(@PathVariable Long orderItemId){
//        return orderItemService.findStoreMenuIdByOrderItemId(orderItemId);
//    }
//
//    @GetMapping("/{orderListId}/order-items")
//    public List<OrderItem> findOrderItemsByOrderListId(@PathVariable Long orderListId) {
//        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrderListId(orderListId);
//        return orderItems;
//    }
}
