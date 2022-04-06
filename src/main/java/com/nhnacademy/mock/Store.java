package com.nhnacademy.mock;

public class Store {

    public int pay(int originalMoney, Customer customer){
        customer.sub(originalMoney);
        return customer.getMoney();
    }
}
