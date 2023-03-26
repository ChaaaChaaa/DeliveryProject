package com.programmers.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(length = 30, nullable = false)
    private String storeName;

    @Column(length = 20, nullable = false)
    private String category;

    @Column(length = 11, nullable = false)
    private int reviewCount;
    private float rating;

    @Builder
    public Store(Long storeId, String storeName, String category, int reviewCount, float rating) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.category = category;
        this.reviewCount = reviewCount;
        this.rating = rating;
    }

    public void update(String storeName, String category, int reviewCount, float rating) {
        this.storeName = storeName;
        this.category = category;
    }
}
