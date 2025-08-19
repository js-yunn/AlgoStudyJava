package TopKFrequentElements;

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 숫자마다 빈도수 계산
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 빈도수마다 숫자 넣을 버킷 배열 buckets
        List<Integer>[] buckets = new List[nums.length + 1];

        // 빈도수에 따라 숫자 해당 버킷에 add
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        // 버킷을 뒤에서부터 순회하며 K개의 frequent한 요소 찾기
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size()<k; i--) {
            if (buckets[i]==null) continue;
            result.addAll(buckets[i]);
        }
        
        // list->array 변환
        int[] res = new int[k];
        for (int i=0; i<k; i++) {
        	res[i]=result.get(i);
        }
        // 스트림 API를 사용해 리스트에서 배열로 변환 더 쉽게 가능
        // return result.stream().mapToInt(Integer::intValue).toArray(); 
        return res;
    }
}