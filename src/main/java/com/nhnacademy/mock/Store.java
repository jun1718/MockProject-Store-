package com.nhnacademy.mock;

import java.util.Map;

public class Store {


    public int pay(int originalMoney, Customer customer){
        customer.sub(originalMoney);
        return customer.getMoney();
    }

    public void initAccounts() {

    }
    
    public Map<String, Customer> getAccounts() {
        return null;
    }
}
