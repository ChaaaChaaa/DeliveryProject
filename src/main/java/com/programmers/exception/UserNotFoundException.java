package com.programmers.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("해당 사용자가 없습니다.");
    }
}
