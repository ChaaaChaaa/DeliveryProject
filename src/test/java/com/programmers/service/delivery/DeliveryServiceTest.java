package com.programmers.service.delivery;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.domain.delivery.Delivery;
import com.programmers.dto.delivery.DeliveryResponseDto;
import com.programmers.repository.delivery.DeliveryRepository;

@SpringBootTest
@Transactional
class DeliveryServiceTest {
	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Test
	void save() {
		Delivery delivery = basicDeliveryData();
		Delivery savedDelivery = deliveryRepository.save(delivery);
		assertNotNull(savedDelivery);
	}

	@Test
	void findById() {
		//given
		Delivery delivery = basicDeliveryData();
		Delivery savedDelivery = deliveryRepository.save(delivery);

		//when
		DeliveryResponseDto findDelivery = deliveryService.findByDeliveryId(savedDelivery.getDeliveryId());

		//then
		assertEquals(savedDelivery.getDeliveryId(), findDelivery.getDeliveryId());

	}

	private Delivery basicDeliveryData() {
		return Delivery.builder()
			.build();
	}
}
