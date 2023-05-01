package com.programmers.service.orderList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequest;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.delivery.DeliveryService;
import com.programmers.service.user.UserService;

@Transactional
@SpringBootTest
@Sql("/dataTest.sql")
class OrderListServiceTest {

	@Autowired
	OrderListRepository orderListRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DeliveryRepository deliveryRepository;

	@Autowired
	OrderListService orderListService;

	@Autowired
	UserService userService;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	OrderItemRepository orderItemRepository;

	@BeforeEach
	void clean() {
		//        orderRepository.deleteAll();
		//        userRepository.deleteAll();
		//        deliveryRepository.deleteAll();
	}

	@Test
	@DisplayName("저장된 주문 조회")
	void save() {
		OrderRequest orderRequest = new OrderRequest(1L, 1L,
			Payment.CASH, OrderState.READY, 10000,
			List.of(new OrderItem(null, new StoreMenu(1L, null, null), 1L, 10000)));
		OrderList savedOrderList = orderListService.save(orderRequest);
		List<OrderItem> orderItems = orderItemRepository.findByOrderListId(savedOrderList.getOrderListId());

		assertEquals(10000, savedOrderList.getTotalPrice());
		assertEquals("차차네", orderItems.get(0).getStoreMenu().getStore().getStoreName());
	}

	@Test
	void findById() {
		OrderRequest orderRequest = new OrderRequest(1L, 1L,
			Payment.CASH, OrderState.READY, 10000,
			List.of(new OrderItem(null, new StoreMenu(1L, null, null), 1L, 10000)));
		OrderList savedOrderList = orderListService.save(orderRequest);
		assertNotNull(orderRequest);
		assertEquals("chacha", savedOrderList.getUser().getNickName());
		assertEquals(Payment.CASH, orderRequest.getPaymentMethod());
	}

	@Test
	void findDeliveryByOrderListId() {
		User user = basicUserData();
		UserRequestDto userRequestDto = UserRequestDto.of(user);
		User savedUser = userService.save(userRequestDto);

		Delivery delivery = basicDelivery();
		Delivery savedDelivery = deliveryRepository.save(delivery);

		OrderList orderList = basicOrderData(savedUser, savedDelivery);
		OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
		OrderList savedOrderList = orderListService.save(orderRequestDto);

		Delivery findDelivery = orderListService.findDeliveryByOrderListId(savedOrderList.getOrderListId());

		assertNotNull(findDelivery);
		assertEquals(savedOrderList.getDelivery().getDeliveryId(), findDelivery.getDeliveryId());
	}

	@Test
	void deleteById() {
		User user = basicUserData();
		UserRequestDto userRequestDto = UserRequestDto.of(user);
		User savedUser = userService.save(userRequestDto);

		Delivery delivery = basicDelivery();
		Delivery savedDelivery = deliveryRepository.save(delivery);

		OrderList orderList = basicOrderData(savedUser, savedDelivery);
		OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
		OrderList savedOrderList = orderListService.save(orderRequestDto);

		orderListService.deleteById(savedOrderList.getOrderListId());

		//then
		Optional<OrderList> orderListId = orderListRepository.findByOrderListId(savedOrderList.getOrderListId());
		Assertions.assertTrue(orderListId.isEmpty());
	}

	private OrderList basicOrderData(User user, Delivery delivery) {
		return OrderList.builder()
			.user(user)
			.delivery(delivery)
			.paymentMethod(Payment.CREDIT_CARD)
			.orderState(OrderState.READY)
			.totalPrice(10000)
			.build();
	}

	private Delivery basicDelivery() {
		return Delivery.builder()
			.build();
	}

	private User basicUserData() {
		return User.builder()
			.userName("test")
			.password("1234")
			.nickName("차차")
			.phoneNumber("11111111111")
			.grade(Grade.NORMAL)
			.role(Role.CUSTOMER)
			.build();
	}

	//    private OrderItem basicOrderItem(){
	//        return OrderItem.builder()
	//                .
	//    }
}
