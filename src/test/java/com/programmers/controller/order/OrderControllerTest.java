package com.programmers.controller.order;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.food.Food;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.order.OrderState;
import com.programmers.domain.order.Payment;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.store.Store;
import com.programmers.domain.storeMenu.StoreMenu;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.food.FoodRequestDto;
import com.programmers.dto.order.OrderRequestDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.storeMenu.StoreMenuRequestDto;
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


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);

        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);

        Store store = basicStoreData();
        StoreRequestDto storeRequestDto = StoreRequestDto.of(store);
        Store savedStore = storeService.save(storeRequestDto);

        StoreMenu storeMenu = basicStoreMenuData(savedFood,savedStore);
        StoreMenuRequestDto storeMenuRequestDto = StoreMenuRequestDto.of(storeMenu);
        StoreMenu savedStoreMenu = storeMenuService.save(storeMenuRequestDto);

        //OrderList orderList = basicOrderData(savedUser, savedDelivery);
        //OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        //OrderList savedOrderList = orderListService.save(orderRequestDto);

        String json = "{\"userId\": 1, \"deliveryId\": 1, \"paymentMethod\": \"CASH\", \"orderState\": \"READY\",\"totalPrice\": 10000,\"orderItems\": [\n" +
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
        //OrderList savedOrderList = orderListService.save(orderRequestDto);


//        mockMvc.perform(get("/order-list/{orderListId}",savedOrderList.getOrderListId()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.user.userId").value(savedOrderList.getUser().getUserId()))
//                .andExpect(jsonPath("$.delivery.deliveryId").value(savedOrderList.getDelivery().getDeliveryId()))
//                .andExpect(jsonPath("$.paymentMethod").value(savedOrderList.getPaymentMethod().toString()))
//                .andExpect(jsonPath("$.orderState").value(savedOrderList.getOrderState().toString()))
//                .andExpect(jsonPath("$.totalPrice").value(savedOrderList.getTotalPrice()));
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
        //OrderList savedOrderList = orderListService.save(orderRequestDto);

//        mockMvc.perform(get("/order-list/search/user/{orderListId}",savedOrderList.getOrderListId()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.userId").value(savedOrderList.getUser().getUserId()))
//                .andExpect(jsonPath("$.userName").value(savedOrderList.getUser().getUserName()))
//                .andExpect(jsonPath("$.password").value(savedOrderList.getUser().getPassword()));
    }

    @Test
    void searchDeliveryByOrderListId() throws Exception {
        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);

        OrderList orderList = basicOrderData(savedUser,savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        //OrderList savedOrderList = orderListService.save(orderRequestDto);

//        mockMvc.perform(get("/order-list/search/delivery/{orderListId}",savedOrderList.getOrderListId()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.deliveryId").value(savedOrderList.getDelivery().getDeliveryId()));
    }

    private StoreMenu basicStoreMenuData(Food food, Store store) {
        return StoreMenu.builder()
                .food(food)
                .store(store)
                .build();
    }

    private Food basicFoodData() {
        return Food.builder()
                .price(1000)
                .description("맛있는라면")
                .name("라면")
                .build();
    }

    private Store basicStoreData() {
        return Store.builder()
                .category("noodle")
                .storeName("차차네")
                .rating(5.0f)
                .reviewCount(100)
                .build();
    }

    private OrderItem basicOrderItemData(OrderList savedOrderList, StoreMenu savedStoreMenu) {
        return OrderItem.builder()
                .orderList(savedOrderList)
                .storeMenu(savedStoreMenu)
                .quantity(2L)
                .price(1000)
                .build();
    }

    private List<OrderItem> basicOrderItemList(OrderItem orderItem){
        return Arrays.asList(orderItem);
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
