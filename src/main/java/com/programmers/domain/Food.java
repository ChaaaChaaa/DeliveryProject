package com.programmers.domain;


import org.springframework.data.annotation.Id;


import javax.persistence.Column;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

public class Food {
    @Id
    private Long id;

    @Column(length=100, nullable=false)
    private String category;

    @Column(length=500, nullable=false)
    private String name;

    @Column(length=100, nullable=false)
    private int price;

    @Column(columnDefinition="TEXT",nullable=false)
    private String description;
    private String image;

    @Builder
    public Food(Long id, String category, String name, int price, String description, String image) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}
