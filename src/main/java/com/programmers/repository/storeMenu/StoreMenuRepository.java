package com.programmers.repository.storeMenu;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.programmers.domain.storeMenu.StoreMenu;

@Repository
public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {
	@NotNull
	Optional<StoreMenu> findById(@Nullable Long storeMenuId);

	StoreMenu save(@Nullable StoreMenu storeMenu);

	void delete(@Nullable StoreMenu storeMenu);
}
