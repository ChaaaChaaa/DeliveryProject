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

    @Builder
    public DeliveryResponseDto(long deliveryId) {
        this.deliveryId = deliveryId;
    }
}
