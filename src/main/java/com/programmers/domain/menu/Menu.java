package com.programmers.domain.menu;

import com.programmers.domain.store.Store;
import com.programmers.domain.food.Food;

import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId", foreignKey = @ForeignKey(name = "fk_menu_store"))
    private Store store;

    @ManyToOne
    @JoinColumn(name = "foodId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_menu_food"))
    private Food food;

    private String storeName;
    private String foodName;

    @Builder
    public Menu(Long menuId, Store store, Food food) {
        this.menuId = menuId;
        this.store = store;
        this.food = food;
    }

    public void changeStore(Store store) {
        this.store = store;
    }

    public void changeFood(Food food) {
        this.food = food;
    }
}
