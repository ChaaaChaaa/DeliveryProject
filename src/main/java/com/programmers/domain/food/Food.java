package com.programmers.domain.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "Food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "foodId", nullable = false)
	private Long id;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(length = 30, nullable = false)
	private int price;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String image;

	@Builder
	public Food(String name, int price, String description, String image) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void changePrice(int price) {
		this.price = price;
	}

	public void changeDescription(String description) {
		this.description = description;
	}
}
