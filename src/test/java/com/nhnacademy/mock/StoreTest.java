package com.nhnacademy.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StoreTest {
    Store store;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("0004", 10000);
        store = new Store();
    }

    //    @Test
//    void storePayTest() {
////        Customer customer = new Customer("0004",10000);
//
//        assertThat(store.pay(6000, customer)).isEqualTo(4000);
//    }


    @DisplayName("스토어 결재시 포인트 Accumulation 객체에 적립요청 확인에 대한 Test")
    @Test
    void payOfAddAccumulationPointAboutRequestTest() {
        store.addMemberRepository(customer);
        Accumulation accumulation = store.getAccumulation();
        accumulation.initAccumulationPointRepository(store.getMemberRepository().keySet());
        store.pay(2000, customer);
        assertThat(
            accumulation.getAccumulationPointRepository().get(customer.getMemberId())).isEqualTo(
            200);
    }

    @DisplayName("없는 고객 테스트")
    @Test
    void storeCkMemberShipTest() {
        store.initAccounts();
        Customer testCustomer = new Customer(10000);
        assertThat(store.getMemberRepository().get(testCustomer.getMemberId()))
            .isNull();
        assertThatThrownBy(() -> store.checkMemberShip(customer))
            .isInstanceOf(CheckNoMemberException.class)
            .hasMessage("멤버가 아닙니다.");
    }

    @Test
    void storeAddAccountTest() {
//        customer.setMemberId("0003");
        store.addMemberRepository(customer);
        assertThatCode(() -> store.pay(5000, customer))
            .doesNotThrowAnyException();

    }

    @Test
    void storePayOfDeservingTest() {
        assertThatThrownBy(() -> store.pay(5000, customer))
            .isInstanceOf(CheckNoMemberException.class)
            .hasMessage("멤버가 아닙니다.");
    }

    @Test
    void customerConstructTest() {
        Customer customer2 = new Customer("0001", 10000);
        assertThat(customer2.getMemberId()).isEqualTo("0001");
    }

    @DisplayName("돈을 지불하고 지불한 금액만큼 클라이언트의 금액이 줄어드는지 테스트하며 -로 떨어지는 boundary exception을 테스트합니다.")
    @Test
    void customerSubTest() {
        customer.sub(5000);
        assertThat(customer.getMoney()).isEqualTo(5000);

        assertThatThrownBy(() -> customer.sub(11000))
            .isInstanceOf(UnderBoundException.class)
            .hasMessage("잔고 부족");
    }


    @DisplayName("getAccounts 를 했을때 알맞은 반환타입이 나오는지 확인하는 테스트 이며 ,inti을 했을경우 customer 객체들이 확인하는 테스트")
    @Test
    void storeAccountTest() {
        getAccountFix();
        initAccountFix();
    }

    private void initAccountFix() {
        store.initAccounts();
        assertThat(store.getMemberRepository())
            .isNotNull();
        assertThat(store.getMemberRepository().size()).isEqualTo(3);

        Map<String, Customer> map = new HashMap<>();
        Customer customer2 = new Customer(12452);
        map.put("0002", customer2);
        Store store2 = mock(Store.class);
        when(store2.getMemberRepository()).thenReturn(map);
        assertThat(store2.getMemberRepository().get("0002").getMoney()).isEqualTo(12452);


    }

    private void getAccountFix() {
        Store store2 = mock(Store.class);
        Map<String, Customer> map = new HashMap<>();
        map.put("AA", customer);
        when(store2.getMemberRepository()).thenReturn(map);
        store2.initAccounts();
        assertThat(store2.getMemberRepository())
            .isNotNull();
    }

    @Test
    void networkSmsTest() {
        NetworkSMS sms = mock(NetworkSMS.class);

        store.addMemberRepository(customer);
        Accumulation accumulation = store.getAccumulation();
        accumulation.initAccumulationPointRepository(store.getMemberRepository().keySet());
        store.setNet(sms);

        store.pay(2000, customer);

        verify(sms).setSms(any());

    }

    @Test
    void giveReceiptTest() {
        NetworkSMS sms = mock(NetworkSMS.class);
        store.addMemberRepository(customer);
        Accumulation accumulation = store.getAccumulation();
        accumulation.initAccumulationPointRepository(store.getMemberRepository().keySet());
        store.setNet(sms);

        store.pay(2000, customer);
        assertThat(customer.getReceipts()).isNotNull();

        System.out.println(customer.getReceipts());
    }

    @Test
    void UseAccumulationPoint() {

        Customer rich = new Customer("0005", 1000000);
        NetworkSMS sms = mock(NetworkSMS.class);
        store.addMemberRepository(rich);
        Accumulation accumulation = store.getAccumulation();
        accumulation.initAccumulationPointRepository(store.getMemberRepository().keySet());
        store.setNet(sms);

        store.pay(100000, rich);

        store.pay(4000, rich);
        Integer point =
            store.getAccumulation().getAccumulationPointRepository().get(rich.getMemberId());

        assertThat(point).isEqualTo(6000);
        assertThat(rich.getMoney()).isEqualTo(900000);

        store.pay(100000, rich);

        assertThat(rich.getMoney()).isEqualTo(800000);

    }
}