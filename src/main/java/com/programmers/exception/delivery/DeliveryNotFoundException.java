package com.programmers.exception.delivery;

public class DeliveryNotFoundException  extends RuntimeException {
    public DeliveryNotFoundException() {
        super("배달이 진행되고 있지 않습니다.");
    }
}
