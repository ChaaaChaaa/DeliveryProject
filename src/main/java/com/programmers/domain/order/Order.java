package com.programmers.domain.order;

import com.programmers.domain.user.User;
import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.menu.Menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Order {
    @Id
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_order_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "menuId", referencedColumnName = "menuId", foreignKey = @ForeignKey(name = "fk_order_menu"))
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "deliveryId", referencedColumnName = "deliveryId", foreignKey = @ForeignKey(name = "fk_order_delivery"))
    private Delivery delivery;


    @NotNull
    private String paymentMethod;

    @NotNull
    private String state;

    @NotNull
    private int price;

    @Builder
    public Order(Long orderId, User user, Menu menu, Delivery delivery, String paymentMethod, String state, int price) {
        this.orderId = orderId;
        this.user = user;
        this.menu = menu;
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.state = state;
        this.price = price;
    }
}
