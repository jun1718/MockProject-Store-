package com.nhnacademy.mock;

import java.util.HashMap;
import java.util.Map;

public class Store{
    private Map<String, Customer> memberRepository = new HashMap<>();
    private Accumulation accumulation = new Accumulation();
    private NetworkSMS net = null;

    public Store() {
        initAccounts();
    }

    public void setNet(NetworkSMS net) {
        this.net = net;
    }

    public int pay(int originalMoney, Customer customer){
        checkMemberShip(customer);

        Integer point = accumulation.getAccumulationPointRepository().get(customer.getMemberId());

        if (point < 10000 || point < originalMoney) {
            customer.sub(originalMoney);
            accumulation.addAccumulationPointRepository(originalMoney,customer.getMemberId());
            net.setSms(originalMoney + "원 결제되었습니다.");
            giveReceipt(customer, originalMoney);
            return customer.getMoney();
        }

        accumulation.subAccumulationPointRepository(originalMoney,customer.getMemberId());
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

    public Accumulation getAccumulation() {
        return this.accumulation;
    }

    public void giveReceipt(Customer customer, int originalMoney) {
        Integer point = accumulation.getAccumulationPointRepository().get(customer.getMemberId());
        StringBuilder sb = new StringBuilder();
        sb.append("*** 영수증 ***\n");
        sb.append("지불금액 : " + originalMoney);
        sb.append("\n");
        sb.append("현재 잔액 : " + customer.getMoney());
        sb.append("\n");
        sb.append("회원번호 : " + customer.getMemberId());
        sb.append("\n");
        sb.append("적립금 : " + point);
        sb.append("\n");
        sb.append("*** 영수증 끝(다음에 또 찾아주세요) ***");
        sb.append("\n");
        customer.setReceipts(sb.toString());
    }
}
