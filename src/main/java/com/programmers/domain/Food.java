package com.programmers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private int price;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
}
