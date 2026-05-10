package io.eddie1031.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AbilityRepository {

    private final Map<String, Integer> db = new HashMap<>();

    // 경험치 추가
    public void applyExp(String abilityName, int exp) {
        db.replace(abilityName, exp);
    }

    // 경험치 조회
    public int getCurrentExp(String abilityName) {
        db.putIfAbsent(abilityName, 0);
        return db.get(abilityName);
    }


}
