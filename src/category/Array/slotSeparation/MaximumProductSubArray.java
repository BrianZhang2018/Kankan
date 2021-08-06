package category.Array.slotSeparation;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 *
 Slot separation solution:
 First, if there's no zero in the array, then the subarray with maximum product must start with the first element or end with the last element.
 And therefore, the maximum product must be some prefix product or suffix product. So in this solution, we compute the prefix product A and suffix product B,
 and simply return the maximum of A and B.

 Why? Here's the proof:
 Say, we have a subarray A[i : j](i != 0, j != n) and the product of elements inside is P. Take P > 0 for example: if A[i] > 0 or A[j] > 0, then obviously, we should extend this subarray to include A[i] or A[j];
 if both A[i] and A[j] are negative, then extending this subarray to include both A[i] and A[j] to get a larger product. Repeating this procedure and eventually we will reach the beginning or the end of A.
 What if there are zeroes in the array? Well, we can split the array into several smaller ones. That's to say, when the prefix product is 0, we start over and compute prefix profuct from the current element instead.
 And this is exactly what A[i] *= (A[i - 1]) or 1 does.
 * Created by brianzhang on 6/14/20.
 */
public class MaximumProductSubArray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
        System.out.println(maxProduct(new int[]{-2,0,-1}));
    }

    // Slot separation solution - similar with ProductExceptSelf.java
    public static int maxProduct(int[] nums) {
        int l = 0, r=0; int max = Integer.MIN_VALUE;
        for(int i=0; i< nums.length; i++){
            l = (l==0 ? 1 : l) * nums[i];               // prefix product, the separation is "0"
            r = (r==0 ? 1 : r) * nums[nums.length-i-1]; // suffix product
            max = Math.max(max, Math.max(l, r));
        }
        return max;
    }

    //My DP solution - time exceed limit
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
