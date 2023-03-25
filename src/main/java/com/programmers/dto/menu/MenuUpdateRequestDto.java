package com.programmers.dto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuUpdateRequestDto {
    private Long storeId;
    private Long id;

    @Builder
    public MenuUpdateRequestDto(Long storeId, Long id) {
        this.storeId = storeId;
        this.id = id;
    }
}
