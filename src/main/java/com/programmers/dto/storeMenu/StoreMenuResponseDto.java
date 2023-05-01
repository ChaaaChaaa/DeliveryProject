package com.programmers.dto.storeMenu;

import com.programmers.domain.storeMenu.StoreMenu;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class StoreMenuResponseDto {
	private String storeName;
	private String foodName;

	@Builder
	public StoreMenuResponseDto(String storeName, String foodName) {
		this.storeName = storeName;
		this.foodName = foodName;
	}

	public static StoreMenuResponseDto convertIdToName(StoreMenu storeMenu) {
		return StoreMenuResponseDto.builder()
			.foodName(storeMenu.getFood().getName())
			.storeName(storeMenu.getStore().getStoreName())
			.build();
	}
}
