package com.nhnacademy.mock;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Customer> memberRepository = new HashMap<>();

    public Store() {
        initAccounts();
    }

    public int pay(int originalMoney, Customer customer){
        checkMemberShip(customer);
        customer.sub(originalMoney);
        return customer.getMoney();
    }

    public void initAccounts() {
        addMemberRepository(new Customer("0001",10000));
        addMemberRepository(new Customer("0002",15000));
        addMemberRepository(new Customer("0003",20000));
    }
    
    public Map<String, Customer> getMemberRepository() {
        return memberRepository;
    }

    public boolean checkMemberShip(Customer customer) {
        if(memberRepository.get(customer.getMemberId())==null){
            throw new CheckNoMemberException("멤버가 아닙니다.");
        }
        return true;
    }

    public void addMemberRepository(Customer customer) {
        memberRepository.put(customer.getMemberId(), customer);
    }
}
