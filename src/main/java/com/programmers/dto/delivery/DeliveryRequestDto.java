package com.programmers.dto.delivery;

import javax.validation.constraints.NotNull;

import com.programmers.domain.delivery.Delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryRequestDto {
	@NotNull
	private long deliveryId;

	@Builder
	public DeliveryRequestDto(long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Delivery toEntity() {
		return Delivery.builder()
			.build();
	}
}
