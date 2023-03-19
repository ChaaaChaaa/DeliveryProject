package com.programmers.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  //선언된 모든 final 필드가 포함된 생성자를 생성
public class FoodRequestDto {
    private String category;
    private String name;
    private int price;
    private String description;
    private String image;
}
