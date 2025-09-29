package category.Array.prefixSum;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Given an array of integers and an integer k, you need to find the total number of continuous subArrays whose sum equals to k.
 *
 * intuition:
 * if the cumulative sum up to two indices, say index "i" and "j" is at a difference of "K" 
 * i.e. if sum[i]−sum[j]=K, the sum of elements lying between indices i and j is k.
 *
 * Created by brianzhang on 12/8/18.
 */
public class SubArraySumPrefixSum {
    public static void main(String[] args) {
        System.out.println(subArraySum(new int[]{1,1,1, -1, -1, 1, 2}, 2));
    }

    public static int subArraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, 1); // (i.e., prefixSum - k = 0), the subarry start from first element
        int count = 0, prefixSum = 0;
        for (int num: nums) {
            prefixSum += num;
            count += map.getOrDefault(prefixSum - k, 0); 
            // that means PrevPrefixSum + k = CurrPrefixSum, so found a "k" which lying between prev and curr prefixSum
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
