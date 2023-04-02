package com.programmers.exception.security;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("인증되지 않은 토큰입니다.");
    }
}