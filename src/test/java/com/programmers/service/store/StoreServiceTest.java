package com.programmers.service.store;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.programmers.domain.store.Store;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.store.StoreResponseDto;
import com.programmers.repository.store.StoreRepository;

@SpringBootTest
@Transactional
class StoreServiceTest {
	@Autowired
	StoreRepository storeRepository;

	@Autowired
	StoreService storeService;

	@BeforeEach
	void clean() {
		storeRepository.deleteAll();
	}

	@Test
	@DisplayName("저장된 가게 조회")
	void save() {
		Store store = basicStoreData();
		Store newStore = storeRepository.save(store);
		assertEquals(newStore.getStoreName(), store.getStoreName());
	}

	@Test
	@DisplayName("Id로 가게 조회")
	void findByStoreId() {
		Store store = basicStoreData();
		Store newStore = storeService.save(StoreRequestDto.of(store));

		StoreResponseDto storeResponseDto = storeService.findByStoreId(newStore.getStoreId());

		assertNotNull(storeResponseDto);
		assertEquals("차차네", newStore.getStoreName());
		assertEquals("noodle", newStore.getCategory());
	}

	@Test
	@DisplayName("Id 조회 실패")
	void findNonExistingStoreById() {
		Store store = basicStoreData();
		Store newStore = storeService.save(StoreRequestDto.of(store));
		Long nonExistingId = newStore.getStoreId() + 100;

		//when & then
		ResponseStatusException thrown = assertThrows(ResponseStatusException.class,
			() -> storeService.findByStoreId(nonExistingId));
		assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
	}

	@Test
	void findByStoreName() {
		//given
		Store store = basicStoreData();
		Store newStore = storeService.save(StoreRequestDto.of(store));

		//when
		StoreResponseDto targetStore = storeService.findByStoreName(newStore.getStoreName());

		//then
		Assertions.assertEquals(targetStore.getStoreName(), newStore.getStoreName());
	}

	@Test
	void update() {
		//given
		String modifiedStoreName = "니노네";
		String modifiedStoreCategory = "koreanFood";

		Store store = storeRepository.save(dummyStoreData());
		Long storeId = store.getStoreId();

		Store updatedStore = Store.builder()
			.storeName(modifiedStoreName)
			.category(modifiedStoreCategory)
			.build();

		StoreRequestDto storeRequestDto = StoreRequestDto.of(updatedStore);

		//when
		storeService.update(storeId, storeRequestDto);

		//then
		Store targetStore = storeRepository.findById(storeId).orElseThrow();

		assertEquals(modifiedStoreName, targetStore.getStoreName());
		assertEquals(modifiedStoreCategory, targetStore.getCategory());
	}

	@Test
	void deleteById() {
		Store store = basicStoreData();
		Store newStore = storeRepository.save(store);

		storeRepository.delete(newStore);

		Optional<Store> findId = storeRepository.findByStoreId(newStore.getStoreId());
		Assertions.assertTrue(findId.isEmpty());
	}

	private Store basicStoreData() {
		return Store.builder()
			.category("noodle")
			.storeName("차차네")
			.rating(5.0f)
			.reviewCount(100)
			.build();
	}

	private Store dummyStoreData() {
		return Store.builder()
			.category("dumpling")
			.storeName("만두네")
			.rating(4.5f)
			.reviewCount(200)
			.build();
	}
}
