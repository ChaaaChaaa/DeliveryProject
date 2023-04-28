package com.programmers.repository.storeMenu;

import com.programmers.domain.storeMenu.StoreMenu;

import com.programmers.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

@Repository
public interface StoreMenuRepository extends JpaRepository<StoreMenu,Long> {
    @NotNull
    Optional<StoreMenu> findById(@Nullable Long storeMenuId);

    Optional<StoreMenu> findByStoreMenuId(Long storeMenuId);

    Store findStoreByStoreMenuId(Long storeMenuId);
    List<StoreMenu> findStoreListByStoreMenuId(Long storeMenuId);

    StoreMenu save(@Nullable StoreMenu storeMenu);

    void delete(@Nullable StoreMenu storeMenu);
}
