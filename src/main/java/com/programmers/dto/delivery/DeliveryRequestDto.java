package com.programmers.dto.delivery;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeliveryRequestDto {
    @NotNull
    private final long deliveryId;
    @NotNull
    private final String deliveryState;

    @Builder
    public DeliveryRequestDto(long deliveryId, String deliveryState) {
        this.deliveryId = deliveryId;
        this.deliveryState = deliveryState;
    }
}
