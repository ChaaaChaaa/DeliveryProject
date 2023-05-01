package com.programmers.controller.store;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.store.StoreResponseDto;
import com.programmers.service.store.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/stores")
@RestController
public class StoreController {
	private final StoreService storeService;

	@PostMapping("/save")
	public void saveStore(@RequestBody StoreRequestDto storeRequestDto) {
		storeService.save(storeRequestDto);
	}

	@GetMapping("/{storeId}")
	public StoreResponseDto searchStoreById(@PathVariable Long storeId) {
		return storeService.findByStoreId(storeId);
	}

	@GetMapping("/search")
	public StoreResponseDto searchStoreByName(@RequestParam("storeName") String storeName) {
		return storeService.findByStoreName(storeName);
	}

	@PutMapping("/{storeId}")
	public void updateStore(@PathVariable Long storeId, @RequestBody StoreRequestDto storeRequestDto) {
		storeService.update(storeId, storeRequestDto);
	}

	@DeleteMapping("/{storeId}")
	public void deleteStoreId(@PathVariable Long storeId) {
		storeService.deleteById(storeId);
	}

}
