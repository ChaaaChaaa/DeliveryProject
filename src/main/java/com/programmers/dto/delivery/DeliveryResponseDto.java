package com.programmers.dto.delivery;

import javax.validation.constraints.NotNull;

import com.programmers.domain.delivery.Delivery;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DeliveryResponseDto {
	@NotNull
	private final long deliveryId;

	@Builder
	public DeliveryResponseDto(long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public static DeliveryResponseDto of(Delivery delivery) {
		return DeliveryResponseDto.builder()
			.deliveryId(delivery.getDeliveryId())
			.build();
	}
}
