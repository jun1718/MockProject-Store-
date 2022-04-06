package com.nhnacademy.mock;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServTest {
    Store store;
    Customer customer;
    @BeforeEach
    void setUp() {

    }
    @Test
    void test(){
        store = new Store();

        customer = new Customer(10000);

        assertThat(store.pay(customer)).isEqualTo(3);
    }
}