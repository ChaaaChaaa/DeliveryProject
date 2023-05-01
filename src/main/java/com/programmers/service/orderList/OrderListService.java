package com.programmers.service.orderList;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequest;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.order.OrderResponseDto;
import com.programmers.exception.delivery.DeliveryNotFoundException;
import com.programmers.exception.user.UserNotFoundException;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class OrderListService {
    private final OrderListRepository orderListRepository;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderItemRepository orderItemRepository;

@Transactional
    public OrderList save(OrderRequest orderRequest) {
        // order save
        User user = userRepository.findByUserId(orderRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        Delivery delivery = deliveryRepository.findById(orderRequest.getDeliveryId()).orElseThrow(DeliveryNotFoundException::new);

        OrderList savedOrderList = OrderList.builder()
  //              .orderListId(orderRequestDto.getOrderListId())
                .user(user)
                .delivery(delivery)
                .paymentMethod(orderRequest.getPaymentMethod())
                .orderState(orderRequest.getOrderState())
                .totalPrice(orderRequest.getTotalPrice())
                .build();
        orderListRepository.save(savedOrderList);

        // orderItemList save
        orderItemRepository.saveAll(orderRequest.getOrderItems(savedOrderList.getOrderListId()));
        return savedOrderList;
    }

    public OrderResponseDto findById(Long orderListId) {
        return OrderResponseDto.of(orderListRepository.findById(orderListId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 주문이 존재하지 않습니다.")));
    }

    public User findUserByOrderListId(Long orderListId){
        OrderList orderList = orderListRepository.findById(orderListId).orElseThrow(UserNotFoundException::new);
        return userRepository.findByUserId(orderList.getUser().getUserId()).orElseThrow(UserNotFoundException::new);
    }


    public Delivery findDeliveryByOrderListId(Long orderListId){
        OrderList orderList = orderListRepository.findById(orderListId).orElseThrow(UserNotFoundException::new);
        return deliveryRepository.findById(orderList.getDelivery().getDeliveryId()).orElseThrow(DeliveryNotFoundException::new);
    }

    public void deleteById(long orderListId){
        orderListRepository.deleteById(orderListId);
    }
}
