package com.programmers.controller.delivery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.dto.delivery.DeliveryRequestDto;
import com.programmers.dto.delivery.DeliveryResponseDto;
import com.programmers.service.delivery.DeliveryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
	private final DeliveryService deliveryService;

	@PostMapping("/save")
	public void saveDelivery(@RequestBody DeliveryRequestDto deliveryRequestDto) {
		deliveryService.save(deliveryRequestDto);
	}

	@GetMapping("/search/{deliveryId}")
	public DeliveryResponseDto searchDeliveryById(@PathVariable Long deliveryId) {
		return deliveryService.findByDeliveryId(deliveryId);
	}
}
