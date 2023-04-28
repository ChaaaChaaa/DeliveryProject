package com.programmers.controller.delivery;

import com.programmers.dto.delivery.DeliveryRequestDto;
import com.programmers.service.delivery.DeliveryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{deliveryId}")
    public DeliveryResponseDto searchDeliveryById(@PathVariable Long deliveryId) {
        return deliveryService.findByDeliveryId(deliveryId);
    }
}
