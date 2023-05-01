package com.programmers.repository.order;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.programmers.domain.order.OrderList;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
	@NotNull
	Optional<OrderList> findByOrderListId(@Nullable Long orderListId);

	OrderList save(@Nullable OrderList orderList);

	void delete(@Nullable OrderList orderList);
}
