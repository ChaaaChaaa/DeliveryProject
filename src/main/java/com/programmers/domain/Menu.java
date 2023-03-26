package com.programmers.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "storeId", foreignKey = @ForeignKey(name = "fk_menu_store"))
    private Store store;

    @ManyToOne
    @JoinColumn(name = "foodId", foreignKey = @ForeignKey(name = "fk_menu_food"))
    private Food food;

    @Builder
    public Menu(Long menuId, Store store, Food food) {
        this.menuId = menuId;
        this.store = store;
        this.food = food;
    }

    public void update(Store store, Food food) {
        this.store = store;
        this.food = food;
    }
}
