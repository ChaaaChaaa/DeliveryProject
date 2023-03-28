package com.programmers.exception;

public class JwtTokenProviderException extends RuntimeException {
    public JwtTokenProviderException(String message) {
        super(message);
    }
}