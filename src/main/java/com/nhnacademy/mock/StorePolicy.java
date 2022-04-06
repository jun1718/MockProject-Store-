package com.nhnacademy.mock;

public class StorePolicy implements PolicyRepository {

    @Override
    public int cal(int money) {
        return (int) (money * 0.1);
    }
}
