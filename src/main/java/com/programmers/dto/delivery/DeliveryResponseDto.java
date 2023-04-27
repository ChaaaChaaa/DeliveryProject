package com.programmers.dto.delivery;

import com.programmers.domain.delivery.Delivery;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class DeliveryResponseDto {
    @NotNull
    private final long deliveryId;
    @NotNull
    private final String deliveryState;

    @Builder
    public DeliveryResponseDto(long deliveryId, String deliveryState) {
        this.deliveryId = deliveryId;
        this.deliveryState = deliveryState;
    }

    public static DeliveryResponseDto of(Delivery delivery){
        return DeliveryResponseDto.builder()
                .deliveryId(delivery.getDeliveryId())
                .deliveryState(delivery.getDeliveryState())
                .build();
    }

    public Delivery toEntity(){
        return Delivery.builder()
                .deliveryId(deliveryId)
                .deliveryState(deliveryState)
                .build();
    }
}
