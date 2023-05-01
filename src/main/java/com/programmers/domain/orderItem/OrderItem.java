package com.programmers.domain.orderItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.programmers.domain.order.OrderList;
import com.programmers.domain.storeMenu.StoreMenu;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class OrderItem {
    @Id
    @Column(name = "orderitemId", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderlistId", nullable = false)
    private OrderList orderList;

    @ManyToOne
    @JoinColumn(name = "storemenuId")
    private StoreMenu storeMenu;

    private Long quantity;
    private int price;

    @Builder
    public OrderItem(OrderList orderList, StoreMenu storeMenu, Long quantity, int price) {
        this.orderList = orderList;
        this.storeMenu = storeMenu;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem toOrderItem(OrderList orderList) {
        return new OrderItem(orderList, storeMenu, quantity, price);
    }
}
