package com.programmers.controller.delivery;

import com.programmers.dto.delivery.DeliveryResponseDto;
import com.programmers.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/save")
    public void saveDelivery(@RequestBody DeliveryResponseDto deliveryResponseDto){
        deliveryService.save(deliveryResponseDto);
    }

    @GetMapping("/{id}")
    public DeliveryResponseDto searchDeliveryById(@PathVariable Long deliveryId){
        return deliveryService.findById(deliveryId);
    }
}
