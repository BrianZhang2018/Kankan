package category.Sort.frequency;

import java.util.*;
/**
 * https://leetcode.com/problems/sort-array-by-increasing-frequency/
 */
public class SortArrayByIncreasingFrequency {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for(int i : nums) {
            // map.put(i, map.getOrDefault(i, 0) + 1);
            map.merge(i, 1, Integer::sum);
        }

        return Arrays.stream(nums).boxed()
                .sorted((a,b) -> map.get(a) != map.get(b) ? map.get(a) - map.get(b) : b - a)
                .mapToInt(n -> n)
                .toArray();
    }

    public int[] frequencySort1(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b)->{
            if(a.getValue() == b.getValue()) {
                return b.getKey() - a.getKey();
            }

            return a.getValue() - b.getValue();
        });

        pq.addAll(map.entrySet());

        int count=0;
        while(!pq.isEmpty()) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            int key = entry.getKey();
            int repeat = entry.getValue();
            for(int j=0; j<repeat; j++) {
                nums[count++] = key;
            }
        }

        return nums;
    }
}
