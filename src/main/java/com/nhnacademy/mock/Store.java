package com.nhnacademy.mock;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Customer> memberRepository = new HashMap<>();
    public int pay(int originalMoney, Customer customer){
        customer.sub(originalMoney);
        return customer.getMoney();
    }

    public void initAccounts() {
        memberRepository.put("0001",new Customer(10000));
    }
    
    public Map<String, Customer> getMemberRepository() {
        return memberRepository;
    }
}
