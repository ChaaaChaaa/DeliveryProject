package com.programmers.domain.delivery;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deliveryId;

    @Builder
    public Delivery(long deliveryId) {
        this.deliveryId = deliveryId;
    }
}