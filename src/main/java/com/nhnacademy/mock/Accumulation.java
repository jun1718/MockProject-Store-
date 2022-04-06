package com.nhnacademy.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Accumulation {
    private Map<String, Integer> accumulationPointRepository = new HashMap<>();

//    public void addAccumulationPointRepository(String memberId) {
//        accumulationPointRepository.put(memberId, 0);
//    }

    public void initAccumulationPointRepository(Set<String> memberIds) {
        for (String memberId : memberIds) {
            accumulationPointRepository.put(memberId, 0);
        }
    }

    public Map<String, Integer> getAccumulationPointRepository() {
        return accumulationPointRepository;
    }
}
