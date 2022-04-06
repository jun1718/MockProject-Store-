package com.nhnacademy.mock;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int money = 0;
    private String memberId = "";
    private List<String> smsList = new ArrayList<>();
    private List<String> receipts = new ArrayList<>();
    public Customer(int money) {
        this.money = money;
    }

    public Customer(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }

    public void sub(int originalMoney) {
        if (originalMoney > this.money) {
            throw new UnderBoundException("잔고 부족");
        }
        this.money -= originalMoney;

    }

    public int getMoney() {
        return this.money;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setSms(String result) {
        smsList.add(result);
    }

    public List<String> getSmsList() {
        return smsList;
    }

    public void setBill(List<String> bill) {
        this.smsList = bill;
    }

    public List<String> getReceipts() {
        return receipts;
    }

    public void setReceipts(String receipt) {
        receipts.add(receipt);
    }
}
