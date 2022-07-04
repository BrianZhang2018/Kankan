package category.Array.slotSeparation;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 *
     A way to understand the reason why the maximum subarray must be a prefix of suffix of the array:
     suppose there's no 0 in the array:
     if number of negative number in array is odd, and left most is i and right most is j, then we can only either keep A[:j] or A[i:] to make it max
     if number of negative number in array is even, then we just multiply all the numbers.
     0 will divide the array to several separated subarrays - Slot separation - e.g. ProductExceptSelf.java

 * Created by brianzhang on 6/14/20.
 */
public class MaximumProductSubArray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
        System.out.println(maxProduct(new int[]{-2,0,-1}));
    }

    public static int maxProduct(int[] nums) {
        int l = 0, r=0; int max = Integer.MIN_VALUE;
        for(int i=0; i< nums.length; i++){
            l = (l==0 ? 1 : l) * nums[i];               // prefix product, the separation is "0"
            r = (r==0 ? 1 : r) * nums[nums.length-i-1]; // suffix product
            max = Math.max(max, Math.max(l, r));
        }
        return max;
    }

    // My DP solution - time exceed limit
    public static int maxProductDP(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        if(nums.length == 1)
            return nums[0];

        int max = Integer.MIN_VALUE;
        int[][] dp = new int[nums.length][nums.length];

        for(int i=0; i<nums.length; i++){
            dp[i][i] = nums[i];
            max = Math.max(dp[i][i], max);
            for(int j=i+1; j<nums.length; j++){
                dp[i][j] = dp[i][j-1] * nums[j]; // dp calculate all the scenarios
                max=Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

}
