package com.programmers.service.delivery;

import com.programmers.domain.delivery.Delivery;
import com.programmers.dto.delivery.DeliveryResponseDto;
import com.programmers.repository.delivery.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public Delivery save(DeliveryResponseDto deliveryResponseDto) {
        return deliveryRepository.save(deliveryResponseDto.toEntity());
    }

    public DeliveryResponseDto findById(Long deliveryId) {
        return DeliveryResponseDto.of(deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 배달이 존대하지 않습니다.")));
    }


}
