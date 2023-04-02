package com.programmers.service.store;


import com.programmers.domain.store.Store;
import com.programmers.dto.store.StoreRequestDto;
import com.programmers.dto.store.StoreResponseDto;
import com.programmers.exception.store.StoreNotFoundException;
import com.programmers.repository.store.StoreRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Store save(StoreRequestDto storeRequestDto) {
        return storeRepository.save(storeRequestDto.toEntity());
    }

    public StoreResponseDto findByStoreId(Long id) {
        return StoreResponseDto.of(storeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 가게가 존재하지 않습니다.")));
    }

    public StoreResponseDto findByStoreName(String storeName) {
        return Stream.ofNullable(storeRepository.findByStoreName(storeName))
                .map(StoreResponseDto::of)
                .findFirst()
                .orElseThrow(StoreNotFoundException::new);
    }


    @Transactional
    public void update(long id, StoreRequestDto storeRequestDto) {
        Store updatedStore = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));
        updatedStore.changeStoreName(storeRequestDto.getStoreName());
        updatedStore.changeCategory(storeRequestDto.getCategory());
        storeRepository.save(updatedStore);
    }

    public void deleteById(long id) {
        storeRepository.deleteById(id);
    }

}
