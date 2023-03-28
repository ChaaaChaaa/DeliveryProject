package com.programmers.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("로그인에 실패하였습니다.");
    }
}
