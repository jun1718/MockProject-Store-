package com.nhnacademy.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StoreTest {
    Store store;
    Customer customer;
    @BeforeEach
    void setUp() {
        customer = new Customer(10000);
    }

    @Test
    void StorePayTest(){
        store = new Store();
        assertThat(store.pay(6000, customer)).isEqualTo(4000);
    }

    @DisplayName("돈을 지불하고 지불한 금액만큼 클라이언트의 금액이 줄어드는지 테스트하며 -로 떨어지는 boundary exception을 테스트합니다.")
    @Test
//    @ValueSource(ints = {5000})
    void CustomerSubTest(){
        customer.sub(5000);
        assertThat(customer.getMoney()).isEqualTo(5000);

        assertThatThrownBy(()->customer.sub(11000))
            .isInstanceOf(UnderBoundException.class)
            .hasMessage("잔고 부족");
    }


}