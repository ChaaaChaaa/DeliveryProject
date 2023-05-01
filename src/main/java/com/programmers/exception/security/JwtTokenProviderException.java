package com.programmers.exception.security;

public class JwtTokenProviderException extends RuntimeException {
	public JwtTokenProviderException(String message) {
		super(message);
	}
}
