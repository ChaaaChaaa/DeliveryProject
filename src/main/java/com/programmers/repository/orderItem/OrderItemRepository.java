package com.programmers.repository.orderItem;


import com.programmers.domain.orderItem.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import lombok.NonNull;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @NonNull
    Optional<OrderItem> findByOrderItemId(@Nullable Long orderItemId);

    @Query("SELECT o FROM OrderItem o WHERE o.orderList.orderListId = :orderListId")
    List<OrderItem> findByOrderListId(@Param("orderListId") Long orderListId);

    OrderItem save(@Nullable OrderItem orderItem);

    void delete(@Nullable OrderItem orderItem);

    void deleteByOrderItemId(Long orderItemId);
}

