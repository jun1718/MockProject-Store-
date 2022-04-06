package com.nhnacademy.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Accumulation{
    private Map<String, Integer> accumulationPointRepository = new HashMap<>();

    public void addAccumulationPointRepository(int originalMoney, String memberId) {
        PolicyRepository storePolicy = new StorePolicy();
//        accumulationPointRepository.put(memberId, accumulationPointRepository.getOrDefault(memberId, storePolicy.cal(originalMoney) + accumulationPointRepository.get(memberId)));
        accumulationPointRepository.put(memberId, storePolicy.cal(originalMoney));
    }

    public void initAccumulationPointRepository(Set<String> memberIds) {
        for (String memberId : memberIds) {
            accumulationPointRepository.put(memberId, 0);
        }
    }

    public Map<String, Integer> getAccumulationPointRepository() {
        return accumulationPointRepository;
    }

    public void subAccumulationPointRepository(int originalMoney, String memberId) {
        int totalPoint = accumulationPointRepository.get(memberId);
        accumulationPointRepository.put(memberId, totalPoint - originalMoney);
    }
}
