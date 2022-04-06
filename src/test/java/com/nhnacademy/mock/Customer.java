package com.nhnacademy.mock;

public class Customer {
    private int money = 0;
    private int memberCode = 0;
    private Coupon coupon = null;
    private String memberId;
    private int grade;
    

    public Customer(int money, int memberCode, Coupon coupon) {
        this.money = money;
        this.memberCode = memberCode;
        this.coupon = coupon;
    }


}
