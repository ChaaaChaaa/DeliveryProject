package com.programmers.domain.review;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.programmers.domain.order.OrderList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "orderListId", foreignKey = @ForeignKey(name = "fk_review_orderlist"))
	private OrderList orderList;

	@Column
	private float rating;

	@Column
	private String content;

	@Column(columnDefinition = "TEXT")
	private String reviewPicture;

	@Builder
	public Review(OrderList orderList, float rating, String content, String reviewPicture) {
		this.orderList = orderList;
		this.rating = rating;
		this.content = content;
		this.reviewPicture = reviewPicture;
	}

}

