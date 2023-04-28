package com.programmers.repository.order;

import com.programmers.domain.order.OrderList;
import com.programmers.domain.orderItem.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    @NotNull
    Optional<OrderList> findByOrderListId(@Nullable Long orderListId);

    List<OrderList> findListByOrderListId(Long orderListId);

    List<OrderItem> findOrderItemByOrderListId(Long orderListId);


    OrderList save(@Nullable OrderList orderList);

    void delete(@Nullable OrderList orderList);
}
