package com.programmers.domain.storeMenu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.programmers.domain.food.Food;
import com.programmers.domain.store.Store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class StoreMenu {

	@Id
	@Column(name = "storeMenuId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeMenuId;

	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId", foreignKey = @ForeignKey(name = "fk_store_menu_store"))
	private Store store;

	@ManyToOne
	@JoinColumn(name = "foodId", referencedColumnName = "foodId", foreignKey = @ForeignKey(name = "fk_store_menu_food"))
	private Food food;

	@Builder
	public StoreMenu(Long storeMenuId, Store store, Food food) {
		this.storeMenuId = storeMenuId;
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
