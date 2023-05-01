package com.programmers.dto.error;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
	private final String errorCode;
	private final String errorMessage;

	public ErrorResponseDto(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
