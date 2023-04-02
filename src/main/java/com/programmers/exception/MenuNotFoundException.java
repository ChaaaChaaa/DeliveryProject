package com.programmers.exception;

public class MenuNotFoundException extends RuntimeException{
    public MenuNotFoundException(){
        super("해당 id의 메뉴가 존재하지 않습니다.");
    }
}
