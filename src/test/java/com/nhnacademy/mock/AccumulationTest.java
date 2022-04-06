package com.nhnacademy.mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccumulationTest {
    Accumulation acc = new Accumulation();
    Store store = new Store();

    @DisplayName("스토어의 기존 고객들을 프로그램이 실행될때 자동으로 적립금 카드를 발급해주는 테스트입니다.(진짜 발급은 아니고 0값으로 추가하는 겁니다. 이해를 위해 이렇게 적었습니다.)")
    @Test
    void initAccumulationPointRepositoryTest() {
        acc.initAccumulationPointRepository(store.getMemberRepository().keySet());
        assertThat(acc.getAccumulationPointRepository().size()).isEqualTo(store.getMemberRepository().size());
    }

    //    @DisplayName("손님이 본인의 적립금이 얼마인지 적립센타에 메시지를 보내면 해당 적립금을 알려주는 테스트입니다.")
//    @Test
//    void getAccumulationPointTest() {
//
//        assertThat(acc.getAccumulationPoint())
//            .isEqualTo();
//    }
}
