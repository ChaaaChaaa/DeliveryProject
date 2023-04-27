package com.programmers.domain.order;

import com.programmers.domain.user.User;
import com.programmers.domain.delivery.Delivery;
import com.programmers.domain.menu.Menu;

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

    @ManyToOne
    @JoinColumn(name = "menuId", referencedColumnName = "menuId", foreignKey = @ForeignKey(name = "fk_order_menu"))
    private Menu menu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveryId", referencedColumnName = "deliveryId", foreignKey = @ForeignKey(name = "fk_order_delivery"))
    private Delivery delivery;


    @NotNull
    private String paymentMethod;

    @NotNull
    private String state;

    @NotNull
    private int price;

    @Builder
    public OrderList(User user, Delivery delivery, Payment paymentMethod, OrderState orderState, int totalPrice) {
        this.user = user;
        this.menu = menu;
        this.delivery = delivery;
        this.paymentMethod = paymentMethod;
        this.state = state;
        this.price = price;
    }
}
