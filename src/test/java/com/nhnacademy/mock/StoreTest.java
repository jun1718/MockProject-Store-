package com.nhnacademy.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
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
        customer = new Customer(10000);
        store = new Store();
    }

    @Test
    void storePayTest() {
        assertThat(store.pay(6000, customer)).isEqualTo(4000);
    }

    @DisplayName("없는 고객 테스트")
    @Test
    void storeCkMemberShipTest() {
        store.initAccounts();
        Customer testCustomer = new Customer(10000);
        assertThat(store.getMemberRepository().get(testCustomer.getMemberId()))
            .isNull();
        assertThatThrownBy(() ->store.checkMemberShip(customer))
            .isInstanceOf(ChackNoMemverException.class)
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
        assertThat(store.getMemberRepository().size()).isEqualTo(1);

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
}