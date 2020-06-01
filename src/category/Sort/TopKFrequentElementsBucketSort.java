package category.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/top-k-frequent-elements
 * Bucket Sort
 *
 * 相似问题: TopKFrequentWords.java - priorityQueue
 *
 * Created by brianzhang on 1/6/19.
 */
public class TopKFrequentElementsBucketSort {

    public static void main(String[] args) {
        for(int i : topKFrequent(new int[]{1,1,2,2,3}, 1)){
            System.out.println(i);
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> counter = new HashMap();

        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }

        List<Integer> result = new ArrayList();
        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {

            if (bucket[i] != null)
                result.addAll(bucket[i]);
        }

        return result;

    }
}