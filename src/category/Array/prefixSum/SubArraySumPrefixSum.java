package category.Array.prefixSum;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Given an array of integers and an integer k, you need to find the total number of continuous subArrays whose sum equals to k.
 *
 * 利用PrefixSum的特点, prefixSum是continuously cumulative 每一个element, 所以 prefixSum[3] - prefixSum[2] = value on index 3.
 *
 * Created by brianzhang on 12/8/18.
 */
public class SubArraySumPrefixSum {

    public static void main(String[] args) {
        System.out.println(subArraySum(new int[]{1,1,1, -1, -1, 1, 2}, 2));
    }

    public static int subArraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, 1);

        int res = 0, prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            res += map.getOrDefault(prefixSum - k, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }
}
