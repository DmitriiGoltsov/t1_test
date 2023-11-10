package com.goltsov.test_t1.services;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomStringService {

    public Map<Character, Integer> getCharacterCount(String str) {
        Map<Character, Integer> result = new HashMap<>();
        char[] chars = str.toCharArray();

        for (char element : chars) {
            if (result.containsKey(element)) {
                result.replace(element, result.get(element) + 1);
            } else {
                result.put(element, 1);
            }
        }

        return sortCountsDesc(result);
    }

    private Map<Character, Integer> sortCountsDesc(Map<Character, Integer> counts) {
        return counts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new)
                );
    }
}
