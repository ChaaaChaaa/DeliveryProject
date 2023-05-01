package com.programmers.exception.store;

public class StoreNotFoundException extends RuntimeException {
	public StoreNotFoundException() {
		super("해당 가게를 찾을 수 없습니다.");
	}
}
