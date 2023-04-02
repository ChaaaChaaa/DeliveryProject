package com.programmers.repository.delivery;

import com.programmers.domain.delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    Optional<Delivery> findById(Long deliveryId);
    Delivery save(Delivery delivery);
    void delete(Delivery delivery);
}
