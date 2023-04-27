package com.programmers.dto.delivery;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeliveryRequestDto {
    @NotNull
    private long deliveryId;

    @Builder
    public DeliveryRequestDto(long deliveryId, String deliveryState) {
        this.deliveryId = deliveryId;
    }


    public Delivery toEntity() {
        return Delivery.builder()
                .build();
    }
}
