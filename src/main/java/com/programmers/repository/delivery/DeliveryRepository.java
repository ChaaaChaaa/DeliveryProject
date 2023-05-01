package com.programmers.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programmers.domain.delivery.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	Delivery findByDeliveryId(Long deliveryId);

	Delivery save(Delivery delivery);
}
