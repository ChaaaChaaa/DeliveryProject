package com.programmers.controller.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.delivery.DeliveryService;
import com.programmers.service.food.FoodService;
import com.programmers.service.orderItem.OrderItemService;
import com.programmers.service.orderList.OrderListService;
import com.programmers.service.store.StoreService;
import com.programmers.service.storeMenu.StoreMenuService;
import com.programmers.service.user.UserService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class OrderControllerTest {

	private MockMvc mockMvc;
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
	StoreMenuService storeMenuService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	FoodService foodService;

	@Autowired
	StoreService storeService;

	@BeforeEach
	void clean() {
		mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderListService)).build();
		orderListRepository.deleteAll();
	}

	@Test
	void save() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		String json =
			"{\"userId\": 1, \"deliveryId\": 1, \"paymentMethod\": \"CASH\", \"orderState\": \"READY\",\"totalPrice\": 10000,\"orderItems\": [\n"
				+
				"        {\"storeMenu\": {\"storeMenuId\": 1}, \"quantity\": 1, \"price\": 1000\n" +
				"        },\n" +
				"        {\"storeMenu\": {\"storeMenuId\": 1}, \"quantity\": 1, \"price\": 9000\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		System.out.println(json);
		mockMvc.perform(post("/order-list/save")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json)
		).andDo(print());
	}

	@Test
	void searchOrderById() throws Exception {
		User user = basicUserData();
		UserRequestDto userRequestDto = UserRequestDto.of(user);
		User savedUser = userService.save(userRequestDto);

		Delivery delivery = basicDelivery();
		Delivery savedDelivery = deliveryRepository.save(delivery);

		OrderList orderList = basicOrderData(savedUser, savedDelivery);
		OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
		OrderList savedOrderList = orderListService.save(orderRequestDto);

		mockMvc.perform(get("/order-list/{orderListId}", savedOrderList.getOrderListId()))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.user.userId").value(savedOrderList.getUser().getUserId()))
			.andExpect(jsonPath("$.delivery.deliveryId").value(savedOrderList.getDelivery().getDeliveryId()))
			.andExpect(jsonPath("$.paymentMethod").value(savedOrderList.getPaymentMethod().toString()))
			.andExpect(jsonPath("$.orderState").value(savedOrderList.getOrderState().toString()))
			.andExpect(jsonPath("$.totalPrice").value(savedOrderList.getTotalPrice()));
	}

	@Test
	void searchUserByOrderListId() throws Exception {
		User user = basicUserData();
		UserRequestDto userRequestDto = UserRequestDto.of(user);
		User savedUser = userService.save(userRequestDto);

		Delivery delivery = basicDelivery();
		Delivery savedDelivery = deliveryRepository.save(delivery);

		OrderList orderList = basicOrderData(savedUser, savedDelivery);
		OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
		OrderList savedOrderList = orderListService.save(orderRequestDto);

		mockMvc.perform(get("/order-list/search/user/{orderListId}", savedOrderList.getOrderListId()))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.userId").value(savedOrderList.getUser().getUserId()))
			.andExpect(jsonPath("$.userName").value(savedOrderList.getUser().getUserName()))
			.andExpect(jsonPath("$.password").value(savedOrderList.getUser().getPassword()));
	}

	@Test
	void searchDeliveryByOrderListId() throws Exception {
		User user = basicUserData();
		UserRequestDto userRequestDto = UserRequestDto.of(user);
		User savedUser = userService.save(userRequestDto);

		Delivery delivery = basicDelivery();
		Delivery savedDelivery = deliveryRepository.save(delivery);

		OrderList orderList = basicOrderData(savedUser, savedDelivery);
		OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
		OrderList savedOrderList = orderListService.save(orderRequestDto);

		mockMvc.perform(get("/order-list/search/delivery/{orderListId}", savedOrderList.getOrderListId()))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.deliveryId").value(savedOrderList.getDelivery().getDeliveryId()));
	}

	private OrderList basicOrderData(User user, Delivery delivery) {
		return OrderList.builder()
			.user(user)
			.delivery(delivery)
			.paymentMethod(Payment.CREDIT_CARD)
			.orderState(OrderState.SHIPPING)
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
}
