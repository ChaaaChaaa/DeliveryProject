package com.programmers.service.orderItem;

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
import com.programmers.dto.orderItem.OrderItemResponseDto;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.repository.order.OrderListRepository;
import com.programmers.repository.orderItem.OrderItemRepository;
import com.programmers.repository.store.StoreRepository;
import com.programmers.repository.storeMenu.StoreMenuRepository;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.delivery.DeliveryService;
import com.programmers.service.food.FoodService;
import com.programmers.service.orderList.OrderListService;
import com.programmers.service.store.StoreService;
import com.programmers.service.user.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderItemServiceTest {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderListService orderListService;

    @Autowired
    StoreService storeService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    FoodService foodService;

    @Autowired
    StoreMenuRepository storeMenuRepository;

    @Autowired
    UserService userService;

    @Autowired
    DeliveryService deliveryService;


    @BeforeEach
    void clean() {
        orderItemRepository.deleteAll();
        storeRepository.deleteAll();
    }


    @Test
    void findByOrderItemId() {
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


        OrderList orderList = basicOrderData(savedUser, savedDelivery);
        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderList);
        OrderList savedOrderList = orderListService.save(orderRequestDto);


        OrderItem orderItem = basicOrderItemData(savedOrderList, savedStoreMenu);
        OrderItemRequestDto orderItemRequestDto = OrderItemRequestDto.of(orderItem);

        OrderItem savedOrderItem = orderItemRepository.save(OrderItemRequestDto.toEntity(orderItemRequestDto));
        //when
        OrderItemResponseDto orderItemResponseDto = orderItemService.findByOrderItemId(savedOrderItem.getOrderItemId());

        //then
        assertEquals(orderItem.getOrderList().getPaymentMethod(), orderItemResponseDto.getOrderList().getPaymentMethod());
        assertEquals(orderItem.getQuantity(), orderItemResponseDto.getQuantity());
        assertEquals(orderItem.getPrice(), orderItemResponseDto.getPrice());


    }

//    @Test
//    void findOrderListIdByOrderItemId() {
//        //given
//        Food food = basicFoodData();
//        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
//        Food savedFood = foodService.save(foodRequestDto);
//
//        Store store = basicStoreData();
//        Store savedStore = storeService.save(StoreRequestDto.of(store));
//
//        StoreMenu storeMenu = StoreMenu.builder()
//                .food(savedFood)
//                .store(savedStore)
//                .build();
//        StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);
//
//
//        User user = basicUserData();
//        UserRequestDto userRequestDto = UserRequestDto.of(user);
//        User savedUser = userService.save(userRequestDto);
//
//        Delivery delivery = basicDelivery();
//        Delivery savedDelivery = deliveryRepository.save(delivery);
//
//
//        OrderList orderListInfo = basicOrderData(savedUser, savedDelivery);
//        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderListInfo);
//        OrderList savedOrderList = orderListService.save(orderRequestDto);
//
//
//        OrderItem orderItem = basicOrderItemData(savedOrderList, savedStoreMenu);
//        OrderItemRequestDto orderItemRequestDto = OrderItemRequestDto.of(orderItem);
//
//        OrderItem savedOrderItem = orderItemRepository.save(OrderItemRequestDto.toEntity(orderItemRequestDto));
//
//        //when
//        OrderList orderList = orderItemService.findOrderListIdByOrderItemId(savedOrderItem.getOrderItemId());
//
//        //then
//        assertAll(
//                () -> {
//                    assertThat(orderList.getUser()).isEqualTo(savedUser);
//                    assertThat(orderList.getDelivery()).isEqualTo(savedDelivery);
//                    assertThat(orderList.getPaymentMethod()).isEqualTo(Payment.CREDIT_CARD);
//                    assertThat(orderList.getOrderState()).isEqualTo(OrderState.SHIPPING);
//                    assertThat(orderList.getTotalPrice()).isEqualTo(10000);
//                }
//        );
//    }

//    @Test
//    void findStoreMenuIdByOrderItemId() {
//        //given
//        Food food = basicFoodData();
//        FoodRequestDto foodRequestDto = FoodRequestDto.of(food);
//        Food savedFood = foodService.save(foodRequestDto);
//
//        Store store = basicStoreData();
//        Store savedStore = storeService.save(StoreRequestDto.of(store));
//
//        StoreMenu storeMenu = StoreMenu.builder()
//                .food(savedFood)
//                .store(savedStore)
//                .build();
//        StoreMenu savedStoreMenu = storeMenuRepository.save(storeMenu);
//
//
//        User user = basicUserData();
//        UserRequestDto userRequestDto = UserRequestDto.of(user);
//        User savedUser = userService.save(userRequestDto);
//
//        Delivery delivery = basicDelivery();
//        Delivery savedDelivery = deliveryRepository.save(delivery);
//
//
//        OrderList orderListInfo = basicOrderData(savedUser, savedDelivery);
//        OrderRequestDto orderRequestDto = OrderRequestDto.of(orderListInfo);
//        OrderList savedOrderList = orderListService.save(orderRequestDto);
//
//
//        OrderItem orderItem = basicOrderItemData(savedOrderList, savedStoreMenu);
//        OrderItemRequestDto orderItemRequestDto = OrderItemRequestDto.of(orderItem);
//
//        OrderItem savedOrderItem = orderItemRepository.save(OrderItemRequestDto.toEntity(orderItemRequestDto));
//
//        //when
//        List<StoreMenu> storeMenus = orderItemService.findStoreMenuIdByOrderItemId(savedOrderItem.getOrderItemId());
//
//        //then
//        assertAll(
//                () -> assertThat(storeMenus.size()).isEqualTo(1),
//                () -> {
//                    StoreMenu firstStoreMenu = storeMenus.get(0);
//                    assertThat(firstStoreMenu.getFood()).isEqualTo(savedFood);
//                    assertThat(firstStoreMenu.getStore()).isEqualTo(savedStore);
//                }
//        );
//
//    }

    @Test
    void deleteByOrderItemId() {
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
        OrderList savedOrderList = orderListService.save(orderRequestDto);


        OrderItem orderItem = basicOrderItemData(savedOrderList, savedStoreMenu);
        OrderItemRequestDto orderItemRequestDto = OrderItemRequestDto.of(orderItem);

        OrderItem savedOrderItem = orderItemRepository.save(OrderItemRequestDto.toEntity(orderItemRequestDto));

        //when
        orderItemService.deleteByOrderItemId(savedOrderItem.getOrderItemId());

        //then
        Optional<OrderItem> findOrderItem = orderItemRepository.findByOrderItemId(savedOrderItem.getOrderItemId());
        Assertions.assertTrue(findOrderItem.isEmpty());

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
                .createdAt(LocalDateTime.now())
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
