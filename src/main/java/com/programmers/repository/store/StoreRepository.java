package com.programmers.repository.store;

import com.programmers.domain.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreId(Long id);

    Store save(Store store);

    Store findByStoreName(String storeName);

    void delete(Store store);
}
