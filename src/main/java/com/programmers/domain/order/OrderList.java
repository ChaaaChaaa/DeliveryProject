package com.programmers.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.orderItem.OrderItem;
import com.programmers.domain.user.User;

import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "OrderList")
public class OrderList {
    @Id
    @Column(name = "orderlistId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderListId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_order_user"))
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveryId", referencedColumnName = "deliveryId", foreignKey = @ForeignKey(name = "fk_order_delivery"))
    private Delivery delivery;


    @NotNull
    @Column(columnDefinition = "ENUM('CREDIT_CARD','CASH')")
    @Enumerated(EnumType.STRING)
    private Payment paymentMethod;

    @NotNull
    @Column(columnDefinition = "ENUM('READY','SHIPPING','DELIVERED')")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @NotNull
    private int totalPrice;

    @Builder
    public OrderList(Long orderListId,User user, Delivery delivery, Payment paymentMethod, OrderState orderState, int totalPrice) {
        this.orderListId = orderListId;
        this.user = user;
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
    }
}
