package com.programmers.controller.orderItem;

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
import com.programmers.dto.orderItem.OrderItemRequestDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.storeMenu.StoreMenuRepository;
import com.programmers.service.food.FoodService;
import com.programmers.service.orderItem.OrderItemService;
import com.programmers.service.orderList.OrderListService;
import com.programmers.service.store.StoreService;
import com.programmers.service.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class OrderItemControllerTest {
    private MockMvc mockMvc;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    StoreMenuRepository storeMenuRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderListService orderListService;

    @BeforeEach
    void clean(){
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderItemController(orderItemService)).build();
        orderItemRepository.deleteAll();
    }

    @Test
    @DisplayName("/get 요청시 db에서 각 주문 요소를 찾아온다.")
    void searchOrderItemByOrderItemId() throws Exception {
        //given
        Food food = basicFoodData();
        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
        Food savedFood = foodService.save(foodRequestDto);

        Store store = basicStoreData();
        Store savedStore = storeService.save(StoreRequestDto.of(store));

        StoreMenu storeMenu = StoreMenu.builder()
                .food(savedFood)
                .store(savedStore)
                .build();
        StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);


        User user = basicUserData();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        Delivery delivery = basicDelivery();
        Delivery savedDelivery = deliveryRepository.save(delivery);


        OrderList orderListInfo = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderListInfo);
        //OrderList savedOrderList = orderListService.save(orderRequestDto);


        //OrderItem orderItem = basicOrderItemData(savedOrderList, savedStoreMenu);
       // OrderItemRequestDto orderItemRequestDto = OrderItemRequestDto.of(orderItem);

        //OrderItem savedOrderItem = orderItemRepository.save(orderItemRequestDto.toEntity());

        //when
//        mockMvc.perform(get("/order-items/{orderItemId}",savedOrderItem.getOrderItemId()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.orderItemId").value(savedOrderItem.getOrderItemId().intValue()))
//                .andExpect(jsonPath("$.quantity").value(savedOrderItem.getQuantity().intValue()))
//                .andExpect(jsonPath("$.price").value(savedOrderItem.getPrice()))
//                .andExpect(jsonPath("$.orderList.orderListId").value(savedOrderList.getOrderListId().intValue()))
//                .andExpect(jsonPath("$.storeMenu.storeMenuId").value(savedStoreMenu.getStoreMenuId().intValue()))
//                .andExpect(jsonPath("$.storeMenu.food.name").value(savedFood.getName()))
//                .andExpect(jsonPath("$.storeMenu.store.storeName").value(savedStore.getStoreName()));
    }


    private OrderItem basicOrderItemData(OrderList savedOrderList, StoreMenu savedStoreMenu) {
        return OrderItem.builder()
                .orderList(savedOrderList)
                .storeMenu(savedStoreMenu)
                .quantity(2L)
                .price(1000)
                .build();
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

    private StoreMenu basicStoreMenuData() {
        return StoreMenu.builder()
                .food(basicFoodData())
                .store(basicStoreData())
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
}
