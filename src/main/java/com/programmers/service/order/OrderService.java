package com.programmers.service.order;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.menu.Menu;
import com.programmers.domain.order.Order;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.order.OrderResponseDto;
import com.programmers.exception.delivery.DeliveryNotFoundException;
import com.programmers.exception.menu.MenuNotFoundException;
import com.programmers.exception.user.UserNotFoundException;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.menu.MenuRepository;
import com.programmers.repository.order.OrderRepository;
import com.programmers.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final DeliveryRepository deliveryRepository;

    public Order save(OrderRequestDto orderRequestDto) {
        User user = userRepository.findByUser(orderRequestDto.getOrderId()).orElseThrow(UserNotFoundException::new);
        Menu menu = menuRepository.findByMenu(orderRequestDto.getMenu().getMenuId()).orElseThrow(MenuNotFoundException::new);
        Delivery delivery = deliveryRepository.findById(orderRequestDto.getDelivery().getDeliveryId()).orElseThrow(DeliveryNotFoundException::new);


        Order savedOrder = Order.builder()
                .orderId(orderRequestDto.getOrderId())
                .user(user)
                .menu(menu)
                .delivery(delivery)
                .paymentMethod(orderRequestDto.getPaymentMethod())
                .state(orderRequestDto.getState())
                .price(orderRequestDto.getPrice())
                .build();

        return orderRepository.save(savedOrder);
    }

    public OrderResponseDto findById(Long orderId) {
        return OrderResponseDto.of(orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 주문이 존재하지 않습니다.")));
    }

    public User findUserByOrderId(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(UserNotFoundException::new);
        return userRepository.findByUser(order.getUser().getUserId()).orElseThrow(UserNotFoundException::new);
    }

    public Menu findMenuByOrderId(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(UserNotFoundException::new);
        return menuRepository.findByMenu(order.getMenu().getMenuId()).orElseThrow(MenuNotFoundException::new);
    }


    public Delivery findDeliveryByOrderId(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(UserNotFoundException::new);
        return deliveryRepository.findById(order.getDelivery().getDeliveryId()).orElseThrow(DeliveryNotFoundException::new);
    }

    public void deleteById(long orderId){
        orderRepository.deleteById(orderId);
    }
}
