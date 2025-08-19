package SortCharByFrequency;

import java.util.*;

class Solution0819 {
    public String frequencySort(String s) {
        // 각 문자의 빈도수 계산
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // 버킷 배열을 생성
        List<Character>[] buckets = new List[s.length() + 1];

        // 빈도수에 따라 문자 buckets에 넣기
        for (char c : frequencyMap.keySet()) {
            int freq = frequencyMap.get(c);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }

        // 버킷을 뒤에서부터 순회하며 빈도수가 높은 순서대로 결과 문자열 생성
        StringBuilder result = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    // 해당 문자를 빈도수(i)만큼 append
                    for (int j = 0; j < i; j++) {
                        result.append(c);
                    }
                }
            }
        }

        return result.toString();
    }
}