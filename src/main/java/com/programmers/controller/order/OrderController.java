package com.programmers.controller.order;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.menu.Menu;
import com.programmers.domain.order.Order;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.order.OrderResponseDto;
import com.programmers.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    public Order save(OrderRequestDto orderRequestDto){
        return orderService.save(orderRequestDto);
    }

}
