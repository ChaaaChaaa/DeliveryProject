package com.programmers.domain.review;

import com.programmers.domain.order.Order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    @OneToOne
    @JoinColumn(name="orderId",referencedColumnName = "orderId",foreignKey = @ForeignKey(name="fk_review_order"))
    private Order order;

    @Column
    private float rating;

    @Column
    private String content;

    @Column(columnDefinition = "TEXT")
    private String reviewPicture;

    @Builder
    public Review(Long reviewId, Order order, float rating, String content, String reviewPicture) {
        this.reviewId = reviewId;
        this.order = order;
        this.rating = rating;
        this.content = content;
        this.reviewPicture = reviewPicture;
    }
}
