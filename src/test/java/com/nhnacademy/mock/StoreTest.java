package com.nhnacademy.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
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
    void test(){
        store = new Store();



        assertThat(store.pay(5000, customer)).isEqualTo(5000);
    }

    @Test
//    @ValueSource(ints = {5000})
    void payTest(){
        customer.sub(5100);
        assertThat(customer.getMoney()).isEqualTo(4900);

        assertThatThrownBy(()->customer.sub(11000))
            .isInstanceOf(UnderBoundException.class)
            .hasMessage("잔고 부족");
    }


}