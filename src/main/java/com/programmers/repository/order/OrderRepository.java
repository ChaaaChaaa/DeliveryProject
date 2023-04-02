package com.programmers.repository.order;

import com.programmers.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @NotNull
    Optional<Order> findById(@Nullable Long orderId);

    Order save(@Nullable Order order);

    void delete(@Nullable Order order);
}
