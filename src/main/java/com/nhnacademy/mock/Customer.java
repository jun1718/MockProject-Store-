package com.nhnacademy.mock;

public class Customer {
    private int money = 0;

    public Customer(int money) {
        this.money = money;
    }

    public void sub(int originalMoney) {
        if(originalMoney> this.money){
            throw new UnderBoundException("잔고 부족");
        }
        this.money -= originalMoney;

    }

    public int getMoney() {
        return this.money;
    }
}
