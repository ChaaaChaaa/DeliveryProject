package com.programmers.domain.delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deliveryId;

    @Column
    private String deliveryState;

    @Builder
    public Delivery(long deliveryId, String deliveryState) {
        this.deliveryId = deliveryId;
        this.deliveryState = deliveryState;
    }
}