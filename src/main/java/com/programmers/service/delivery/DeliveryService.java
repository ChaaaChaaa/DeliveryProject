package com.programmers.service.delivery;

import org.springframework.stereotype.Service;

import com.programmers.domain.delivery.Delivery;
import com.programmers.dto.delivery.DeliveryRequestDto;
import com.programmers.dto.delivery.DeliveryResponseDto;
import com.programmers.repository.delivery.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryService {
	private final DeliveryRepository deliveryRepository;

	public Delivery save(DeliveryRequestDto deliveryRequestDto) {
		return deliveryRepository.save(deliveryRequestDto.toEntity());
	}

	public DeliveryResponseDto findByDeliveryId(Long deliveryId) {
		return DeliveryResponseDto.of(deliveryRepository.findByDeliveryId(deliveryId));
	}
}
