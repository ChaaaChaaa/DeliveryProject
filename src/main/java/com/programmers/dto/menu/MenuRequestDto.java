package com.programmers.dto.menu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuRequestDto {
    private final String storeName;
    private final String foodName;

    @Builder
    public MenuRequestDto(@JsonProperty("storeName") String storeName, @JsonProperty("foodName") String foodName) {
        this.storeName = storeName;
        this.foodName = foodName;
    }
}
