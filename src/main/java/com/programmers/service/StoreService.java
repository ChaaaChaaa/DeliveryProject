package com.programmers.service;


import com.programmers.domain.Store;
import com.programmers.dto.store.StoreResponseDto;
import com.programmers.exception.StoreNotFoundException;
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

    public Store save(Store store) {
        return storeRepository.save(store);
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
    public void update(long id, Store store) {
        Store updatedStore = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        updatedStore.changeStoreName(store.getStoreName());
        updatedStore.changeCategory(store.getCategory());
        storeRepository.save(updatedStore);
    }

    public void deleteById(long id) {
        storeRepository.deleteById(id);
    }

}
