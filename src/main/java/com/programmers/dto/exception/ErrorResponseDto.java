package com.programmers.dto.exception;

import lombok.Builder;
import lombok.Getter;


@Getter
public class ErrorResponseDto {
    private final String status;
    private final String message;


    @Builder
    public ErrorResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
