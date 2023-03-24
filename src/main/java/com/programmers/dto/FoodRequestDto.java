package com.programmers.dto;

import com.programmers.domain.Food;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequestDto {
    @NotNull
    private final Long id;
    @NotNull
    private final String category;
    @NotBlank
    @Length(min = 1, max = 30)
    private final String name;
    @NotBlank
    @Length(min = 1, max = 30)
    private final int price;
    private final String description;
    private final String image;

    @Builder //이거랑 @NoArgsConstructor의 차이는 뭐지?
    public FoodRequestDto(Long id, String category, String name, int price, String description, String image) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Food toEntity() {
        return Food.builder()
                .id(id)
                .category(category)
                .name(name)
                .price(price)
                .description(description)
                .image(image)
                .build();
    }
}
