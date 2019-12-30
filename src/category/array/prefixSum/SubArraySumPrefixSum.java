package category.array.prefixSum;

import java.util.HashMap;

/**
 * PrefixSum
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Given an array of integers and an integer k,
 * you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * The idea behind this approach is as follows: If the cumulative sum(repreesnted by sum[i]sum[i] for sum upto i^{th}ithindex)
 * upto two indices is the same, the sum of the elements lying in between those indices is zero. Extending the same thought further,
 * if the cumulative sum upto two indices, say ii and jj is at a difference of kk i.e. if sum[i] - sum[j] = ksum[i]−sum[j]=k,
 * the sum of elements lying between indices i and j is k.
 *
 * 思路对应prefix的特点， prefix sum是continuous cumulative每一个element, 所以sum[3] - 2 = sum[1],
 * 就意味着 the sum of elements lying between indices 3 and 1 is 2（k）.
 *
 * Created by brianzhang on 12/8/18.
 */
public class SubArraySumPrefixSum {

    public static void main(String[] args) {

        int[] nums = {2,1,2};
        System.out.println(subarraySum(nums, 3));
    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> presum = new HashMap();
        presum.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res += presum.getOrDefault(sum - k, 0);
            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
