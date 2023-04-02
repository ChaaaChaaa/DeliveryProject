package com.programmers.exception.user;

public class DuplicatePhoneNumberException extends RuntimeException{
    public DuplicatePhoneNumberException() {
        super("중복된 전화번호입니다.");
    }
}
