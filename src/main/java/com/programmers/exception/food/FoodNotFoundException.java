package com.programmers.exception.food;

public class FoodNotFoundException extends RuntimeException{
    public FoodNotFoundException(){
        super("해당 id의 음식이 존재하지 않습니다.");
    }
}
